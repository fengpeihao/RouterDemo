# Router
#在项目的build.gradle文件添加 maven {url  "https://cfxc.bintray.com/maven"}
```
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
    }
```
#在module的build.gradle文件添加依赖
```
apply plugin: 'kotlin-kapt'
android {
    ...
    defaultConfig {
    ...
          javaCompileOptions {
                  annotationProcessorOptions {
                      arguments = [
                              ROUTER_MODULE_NAME      : project.getName(),
                              //这个navigation表的id, 如<navigation android:id="@+id/nav_graph_module_one">
                              ROUTER_MODULE_GRAPH_NAME: "nav_graph_module_one"
                     ]
                  }
           }
    }
dependencies {
    kapt 'com.github.fengpeihao.router:router-compiler:1.0.1'
    implementation 'com.github.fengpeihao.router:router-core:1.0.1'
}
```
#给所有在navigation表中的Fragment添加Route注解
```
/**
  * destinationText是Fragment在表中的id 
  * 这边我把destinationText 抽出来放在了RouteConstant中
  * <fragment
  *    android:id="@+id/mainModuleHomeFragment"
  *    android:name="com.cfxc.router.MainModuleHomeFragment"/>
  */
@Route(destinationText = RouteConstant.MAIN_MODULE_HOME_FRAGMENT)
class MainModuleHomeFragment : Fragment()
------------------------------分割线---------------------------------

object RouteConstant {
    const val MAIN_MODULE_HOME_FRAGMENT = "mainModuleHomeFragment"
    const val MAIN_MODULE_FIRST_FRAGMENT = "mainModuleFirstFragment"
    const val MAIN_MODULE_SECOND_FRAGMENT = "mainModuleSecondFragment"
    const val MAIN_MODULE_THIRD_FRAGMENT = "mainModuleThirdFragment"
    const val LOGIN_FRAGMENT = "loginFragment"
    const val PREREQUISITE_FRAGMENT = "prerequisiteFragment"

    //module first
    const val DDA_FRAGMENT = "moduleOneFirstFragment"
    const val TRANSACTION_FRAGMENT = "moduleOneSecondFragment"

    //module second
    const val SCC_FRAGMENT = "moduleTwoFirstFragment"

    //provider
    const val USER_DATA_PROVIDER = "user_data_provider"
}
```
#页面跳转
```
//不带参跳转
Router.getInstance().build(RouteConstant.MAIN_MODULE_FIRST_FRAGMENT)
                .navigation(findNavController())
//带设置pop up to
Router.getInstance().build(destination)
                .navigation(findNavController(),RouteConstant.LOGIN_FRAGMENT,true)
//带bundle跳转
Router.getInstance().build(RouteConstant.SCC_FRAGMENT)
                .with(bundleOf(BundleKeyConstant.KEY_CONTENT to "module one says 'Hello'"))
                .navigation(findNavController())
//带跳转回调
Router.getInstance().build(RouteConstant.TRANSACTION_FRAGMENT)
                .navigation(findNavController(), object : NavigationCallback {
                    override fun onLost(postcard: Postcard?) {
                        //没发现路由页面
                    }

                    override fun onArrival(postcard: Postcard?) {
                        //跳转成功
                    }

                    override fun onInterrupt(postcard: Postcard?) {
                        //跳转被拦截
                    }
                })
注意: 页面跳转的时候一定要传NavController
```
#拦截器的使用
```
//拦截器可以定义多个，拦截器是根据 priority的值从大到小依次执行
@Interceptor(priority = 2, name = "loginInterceptor")
class LoginInterceptor : IInterceptor {
    val TAG = "LoginInterceptor"

    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        if (checkNeedLogin(postcard.destinationText)) {
            postcard.prerequisiteDestinationGraph = "nav_graph"
            postcard.prerequisiteDestination = RouteConstant.LOGIN_FRAGMENT
        }
        callback.onContinue(postcard)
    }

    override fun init(context: Context?) {
        Log.e(TAG, "LoginInterceptor init")
    }
}
```
#模块间数据共享Provider的使用
先在common library定义共享的provider接口
```
interface IUserDataProvider:IProvider {
    fun getUserName():String
}
```
然后在要共享数据的module中实现这个接口
```
@Route(destinationText = RouteConstant.USER_DATA_PROVIDER)
class UserDataProvider: IUserDataProvider {

    override fun getUserName():String{
        return "Tom"
    }
}
```
在其他模块就可以这样用
```
val userDataProvider = Router.getInstance().build(RouteConstant.USER_DATA_PROVIDER)
                .navigation() as IUserDataProvider
Toast.makeText(requireContext(), userDataProvider.getUserName(), Toast.LENGTH_SHORT).show()
```
#对了，从main Module跳转到其他module页面时要先跳转到对应的navigation graph，所以其他module的navigation graph的startDestination Fragment要做一个该module的跳转分发器
```
class ModuleOneNavigatorFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //Constants.KEY_DESTINATION_ID 是Router内部定义的bundle Key，用来传递跳转的destinationId
            //这里传递过来的就是在前面页面要跳转到的页面的destinationId
            val destinationId = it.getInt(Constants.KEY_DESTINATION_ID, R.id.moduleOneFirstFragment)
            findNavController().navigate(
                destinationId, it,
                NavOptions.Builder().setPopUpTo(R.id.moduleOneNavigatorFragment, true)
                    .setEnterAnim(R.anim.slide_in).setExitAnim(R.anim.slide_out).build()
            )
        } ?: run {
            findNavController().navigate(
                R.id.moduleOneFirstFragment, null,
                NavOptions.Builder().setPopUpTo(R.id.moduleOneNavigatorFragment, true)
                    .setEnterAnim(R.anim.slide_in).setExitAnim(R.anim.slide_out).build()
            )
        }
    }
}
```
