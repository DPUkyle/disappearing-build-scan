## build-scan behavior for ivy repos with null url

### Existing issues
This may be a reopen of:
 * gradle/gradle#7057
 * gradle/gradle#7260

## Summary

The task `:unpackTool` will be invoked; it resolves a dynamically-added configuration 
within a closure as part of a task input.  The source is at `buildSrc/src/main/groovy/com/kylemoore/DummyPlugin.groovy`

### Steps to reproduce
This repo ships with Gradle 4.8.1 and build scan plugin 1.16

 1. `$ ./gradlew unpackTool` <-- everything's fine, a build-scan is uploaded to gradle.com
 2. `$ ./gradlew wrapper --gradle-version 4.10.3` 
 3. `$ ./gradlew unpackTool` <-- "A build scan cannot be produced..." but this is known, see gradle/gradle#7057
 4. `$ ./gradlew wrapper --gradle-version 5.1.1`
 5. `$ ./gradlew unpackTool` <-- "This version of Gradle requires version 2.0.2 of the build scan plugin or later."
 6. `$ ./gradlew unpackTool -PbuildScanPluginVersion=2.1` <-- success
 
## Conclusion
This is a non-issue; gradle/gradle#7057 adequately fixes the problem.

Handling of a ivy repo with a null url field (which is a valid pattern) was legitimately broken in Gradle 4.10.*

However the combination of build tool 5.+ and build scan plugin 2.+ fixes the issue.