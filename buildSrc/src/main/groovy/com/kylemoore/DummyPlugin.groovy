package com.kylemoore

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.Dependency
import org.gradle.api.tasks.Copy

class DummyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.configurations.create('tool')
        Dependency toolDep = project.dependencies.create('org.apache.geode:apache-geode:1.8.0')
        project.dependencies.add('tool', toolDep)

        Task unpackTool = project.task('unpackTool', type: Copy) {
            from {
                project.tarTree(project.configurations.getByName('tool').singleFile)
            }
            into temporaryDir
        }
    }
}
