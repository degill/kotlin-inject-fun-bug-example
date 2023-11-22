package com.example.kotlininjectbugexample.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Inject
import me.tatarka.inject.annotations.Provides

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = AppComponent::class.create()
        appComponent.provideBar()
    }
}

@Component
abstract class AppComponent {
    @Provides
    fun FooImpl.bind(): Foo = this
//    abstract fun provideFoo(): Foo // Will not compile with error "Function invocation 'provideFoo()' expected"
    abstract val provideFoo: Foo // Will compile fine
    abstract fun provideBar(): Bar
}

interface Foo

@Inject
class FooImpl : Foo

@Inject
class Bar(val foo: Foo)