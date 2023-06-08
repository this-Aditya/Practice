<h1>Dagger HILT</h1>
This guide explains the basic concepts of Hilt and its generated containers.
<br><br>
All apps that use Hilt must contain an Application class that is annotated with `@HiltAndroidApp`.
<br>
Hilt can provide dependencies to other <u><b>Android classes (Activities, Services, fragments)</b></u> 
that have the `@AndroidEntryPoint` annotation.<br>
If you annotate an Android class with @AndroidEntryPoint, then you also must annotate Android classes that depend on it. For example, if you annotate a fragment, then you must also annotate any activities where you use that fragment.
<br>
To obtain dependencies from a component, use the `@Inject`annotation to perform field injection.
<br>


