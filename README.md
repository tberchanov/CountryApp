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
Lets try to use this rule in test from `app` module.

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

## Solution: move test utils code to main source directory

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

The previous solution can not be used here, because `data_countries` module contains not only testing code and `testImplementation` can not be used here.
Theoretically we can move all testing code from `data_countries` to separate module, and name it `data_countries_tests`.
In this situation, previous solution can be used, but for me it looks like over-engineering.
For such cases I would use another solution.