//package park.loremipsum.mvpdaggersample;
//
//import org.junit.runners.model.InitializationError;
//
///**
// * Created by hyunwoopark on 2016. 4. 8..
// */
//public class BaseRobolecricGradleTestRunner extends RobolectricGradleTestRunner {
//    public BaseRobolecricGradleTestRunner(Class<?> klass) throws InitializationError {
//        super(klass);
//    }
//
////    @Override
////    protected AndroidManifest getAppManifest(Config config) {
////        String buildVariant = (BuildConfig.FLAVOR.isEmpty() ? "" : BuildConfig.FLAVOR + "/") + BuildConfig.BUILD_TYPE;
////        String intermediatesPath = BuildConfig.class.getResource("").toString().replace("file:", "");
////        intermediatesPath = intermediatesPath.substring(0, intermediatesPath.indexOf("/classes"));
////
////        String packageName = "park.loremipsum.mvpdaggersample";
////        String manifestPath = intermediatesPath + "/manifests/full/"+ buildVariant + "/AndroidManifest.xml";
////        String resPath = intermediatesPath + "/res/merged/" + buildVariant;
////        String assetPath = intermediatesPath + "/assets/" + buildVariant;
////
////        return createAppManifest(Fs.fileFromPath(manifestPath),
////                Fs.fileFromPath(resPath),
////                Fs.fileFromPath(assetPath),
////                packageName);
////    }
//}
