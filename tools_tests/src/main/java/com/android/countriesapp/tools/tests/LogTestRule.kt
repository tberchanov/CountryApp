package com.android.countriesapp.tools.tests

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class LogTestRule : TestRule {

    override fun apply(
        base: Statement,
        description: Description
    ) = LogStatement(base, description)

    class LogStatement(
        private val base: Statement,
        private val description: Description
    ) : Statement() {

        override fun evaluate() {
            println("${description.methodName} Before testings")
            base.evaluate()
            println("${description.methodName} After testings")
        }
    }
}