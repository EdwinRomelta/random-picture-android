package com.edwin.randompicture.ui.di.module.worker

import androidx.work.Worker
import dagger.MapKey
import kotlin.reflect.KClass

@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class WorkerKey(val value: KClass<out Worker>)
