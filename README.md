CountryApp
==========

Example of using tests in Android multi-module project.

## Intro

Nowadays multi-module Android architecture - is a standard and good practice.
Separation modules by features and layers brings to us better build time and well organized architecture.
In the same time unit and integration testing are also an essential part of development.
I faced with a few not very obvious things in combining this two common approaches in project and want to share my knowledge and experience with community.

## Problem

The main pitfall here is that code from tests sources directories can not be used directly from another module.
For example in project you can find very useful LogTestRule, that placed in tools_tests module.
Lets try to use this rule in test from app module.

For this we need to implement tools_tests in the app.
app/build.gradle
dependencies {
    // ...
    testImplementation project(path: ':tools_tests')
    // ...
}
testImplementation is used because dependency on this module is required only for tests, and is redundant for release builds, for example.

Then lets use our rule in tests in the app module. IDE will allow us to do it without any warnings (Android Studio 4.0).
[Picture of test without warnings]
But if we will try to run test from app module, the following compilation error will appear:
Unresolved reference: LogTestRule [Mark as error]

## Solution №1

As tools_tests is a utils module that contains utils stuff only for modules, we can move utils code from test sources to main sources.
[picture of directories tools_tests]
And as now testing code placed not in test sources, we need to change dependency way of JUnit from testImplementation to implementation.
tools_tests/build.gradle
dependencies {
    // ...
    implementation 'junit:junit:4.12'
    // ...
}

So now, everything should works.