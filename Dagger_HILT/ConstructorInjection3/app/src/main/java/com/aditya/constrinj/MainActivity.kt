package com.aditya.constrinj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.constrinj.databinding.ActivityMainBinding
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var myClass: MyClass
    @Inject
    lateinit var gson: Gson
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        println(myClass.doAThing())
    }
}

// We can't inject
//class Myclass @Inject constructor(/*private val gson: Gson //Also Can't inject Third Party Libraries simply like this */) {
//    @Inject
//    lateinit var  myInterfaceImpl: MyInterfaceImpl
//fun doAThing(): String = "Hey, I did ${myInterfaceImpl.doAThing()}"
//}

//class MyDependency @Inject constructor() {
//    fun doAThing(): String = "A Thing!!"
//}


interface MyInterface {
    fun doAThing() : String
}

class MyInterfaceImpl @Inject constructor(
    private val demoString: String
) : MyInterface {
    override fun doAThing() = "A Thing!!.... $demoString"
}


class MyClass @Inject constructor(private val myInterfaceImpl: MyInterface, val gson: Gson) {
    fun doAThing() = "Hey, I did ${myInterfaceImpl.doAThing()}"
}


// Way 1
//@Module
//@InstallIn(ActivityComponent::class) // dependencies of module live as long as activity.
//abstract class MyModule() {
////    @Singleton This will give error because this is scoped tpo whiole application lifecycle.
//    @ActivityScoped // Lifecycle of activity
//    @Binds
//    abstract fun bindDependency(myInterfaceImpl: MyInterfaceImpl): MyInterface
//
//    /*@ActivityScoped
//    @Binds
//    abstract fun bindLibrary(gson: Gson) : Gson
//    Here it got failed.... Just binds don't know how to do this stuff with third Party libraries,
//     */
//}


//Way 2 @Provides

@InstallIn(ActivityComponent::class)
@Module
class MyModule{
    @ActivityScoped
    @Provides
    fun provideSomeString(): String {
        return "Demo String."
    }

    @ActivityScoped
    @Provides
    fun providesMyInterface() : MyInterface {
        return MyInterfaceImpl(provideSomeString())
    }

    @ActivityScoped
    @Provides
    fun providesGson(): Gson{
        return Gson()
    }
}








