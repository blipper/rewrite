/*
 * Copyright 2022 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.gradle;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.gradle.Assertions.buildGradle;
import static org.openrewrite.gradle.Assertions.withToolingApi;

class UpgradeLiteralDependencyVersionTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.beforeRecipe(withToolingApi())
          .recipe(new UpgradeLiteralDependencyVersion("com.google.guava", "guava", "30.x", "-jre"));
    }

    @Test
    void guava() {
        rewriteRun(
          buildGradle(
            """
              plugins {
                id 'java-library'
              }
              
              repositories {
                mavenCentral()
              }
              
              dependencies {
                implementation 'com.google.guava:guava:29.0-jre'
              }
              """,
            """
              plugins {
                id 'java-library'
              }
              
              repositories {
                mavenCentral()
              }
              
              dependencies {
                implementation 'com.google.guava:guava:30.1.1-jre'
              }
              """
          )
        );
    }

    @Test
    void updateVersionInVariable() {
        rewriteRun(
          buildGradle(
            """
              plugins {
                id 'java-library'
              }
              
              def guavaVersion = '29.0-jre'
              repositories {
                mavenCentral()
              }
              
              dependencies {
                implementation "com.google.guava:guava:$guavaVersion"
              }
              """,
            """
              plugins {
                id 'java-library'
              }
              
              def guavaVersion = '30.1.1-jre'
              repositories {
                mavenCentral()
              }
              
              dependencies {
                implementation "com.google.guava:guava:$guavaVersion"
              }
              """
          )
        );
    }

}
