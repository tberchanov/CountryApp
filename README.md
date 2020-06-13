CountryApp
==========

Example of using tests in Android multi-module project.

## Intro

Nowadays multi-module Android architecture - is a standard and good practice.
Separation modules by features and layers brings to us better build time and well organized architecture.
In the same time unit and integration testing are also an essential part of development.
I faced with a few not very obvious things in combining this two common approaches in project and want to share my knowledge and experience with community.

## Problem: How to share utils test code across the modules?

The main pitfall here is that code from tests sources directories can not be used directly from another module.
For example in project you can find very useful `LogTestRule`, that placed in `tools_tests` module.
Lets try to use this rule in the test from `app` module.

![alt text](https://github.com/tberchanov/CountryApp/blob/master/.readme_images/app%20-%20tools_tests%20dependency.png?raw=true)

For this we need to add `tools_tests` dependency in the `app/build.gradle`.
```groovy
dependencies {
    // ...
    testImplementation project(path: ':tools_tests')
    // ...
}
```
`testImplementation` is used because dependency on this module is required only for tests, and is redundant for release builds.

Then lets use our rule in tests in the app module. IDE will allow us to do it without any warnings (Android Studio 4.0).

![alt text](https://github.com/tberchanov/CountryApp/blob/master/.readme_images/test_without_warnings_example.png?raw=true)

But if we will try to run test from app module, the following compilation error will appear:
> Unresolved reference: LogTestRule

As `tools_tests` is a utils module that contains utils stuff only for modules, we can move utils code from test sources to main sources.

![alt text](https://github.com/tberchanov/CountryApp/blob/master/.readme_images/tools_tests_directories_example.png?raw=true)

And as now testing code placed not in test sources, we need to change dependency way of JUnit from `testImplementation` to `implementation` in `tools_tests/build.gradle`.
```groovy
dependencies {
    // ...
    implementation 'junit:junit:4.12'
    // ...
}
```
So now, everything should works. But as I said, such solution can be applied only for test utils modules.
And here we come to the next problem.

## Problem: How to share feature specific utils test code? 

In the module `data_countries` we have tests for repository `RemoteCountriesRepositoryTest` and some utils functions for creating mocks in `CountriesCreator`.
This utils functions can be very useful for `domain_countries` module, as this module has test `CountriesMapperTest`, where entities from `data_countries` should be mocked.

![alt text](https://github.com/tberchanov/CountryApp/blob/master/.readme_images/domain_countries%20-%20data_countries%20dependency.png?raw=true)

The previous solution can not be used here, because `data_countries` module contains not only testing code and `testImplementation` can not be used here.
Theoretically we can move all testing code from `data_countries` to separate module, and name it `data_countries_tests`.
In this situation, previous solution can be used, but for me it looks like over-engineering.  
For such cases I would use another solution.

Lets create shared test sources inside `data_countries` module.  
For this you need to create folder `sharedTest/java/{module package name}` and move there code, that should be shared, `CountriesCreator` in our case.

![alt text](https://github.com/tberchanov/CountryApp/blob/master/.readme_images/data_countries_directories_example.png?raw=true)

Then you need to add the following code inside `android` block in `data_countries/build.gradle`:
```groovy
sourceSets {
        test {
            java.srcDirs += "src/sharedTest/java"
        }
        main {
            if (Boolean.valueOf(project.findProperty("com.android.countriesapp.share.test.enabled"))) {
                java.srcDirs += "src/sharedTest/java"
            }
        }
}
```

There are a few interesting things:
* `java.srcDirs += "src/sharedTest/java"` - just adds path to the list of source directories
* `com.android.countriesapp.share.test.enabled` - is a property that enables or disables shared tests sources in the main sources.  
It is not good to add shared tests sources to the main for release builds, as we don't want to have such code in the final apk.
In the same time we may need shared code in the main for debug builds or CI.  
This property is located in `gradle.properties` file.

If your shared code contains some testings library sdk, you should add this dependencies not only by `testImplementation`, but with `implementation` also.
But again, we don't need to have redundant dependencies for production ready builds, thats why we should enable them only by property.
For this reason, the following code should be written in `data_countries/build.gradle`:

```groovy
dependencies {
    // ...
    testImplementation "org.mockito:mockito-core:3.3.3"
    if (Boolean.valueOf(project.findProperty("com.android.countriesapp.share.test.enabled"))) {
        implementation "org.mockito:mockito-core:3.3.3"
    }
    // ...
}
```

As result, the code from `sharedTest` source directory can be used across multiple modules.

But this solution looks too complicated. We have a lot of overhead stuff like property, shared folder and duplication of dependencies for code from shared folder.  
If you want to get rid of these complexities, you can use the next solution.

## Final solution

For example, we need to use `LoadCountriesUseCaseCreator` from `domain_countries` module in the `app` module.

![alt text](https://github.com/tberchanov/CountryApp/blob/master/.readme_images/app%20-%20domain_counttries%20dependency.png?raw=true)

For this purpose we can write the following in the `app/build.gradle`:

```groovy
android {
    // ...
    sourceSets {
        test.java.srcDirs += ["${project(':domain_countries').projectDir}/src/test/java"]
    }
}
```

And that's it, just almost one line solution for this problem, that seemed not trivial at the beginning.  
I hope this project will help you. If you have other ideas how this problem can be solved, be sure to write me.  
Thanks for reading =)