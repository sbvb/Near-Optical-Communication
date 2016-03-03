# Near-Optical-Communication
Make 2 mobile devices communicate; a dialog via QR code, POC (Proof Of Concept)


Uses library zxing
https://github.com/zxing/zxing
To just use it, add line below to app/build.gradle
```
compile 'com.google.zxing:core:3.2.1'
compile 'me.dm7.barcodescanner:zxing:1.8.4'
```

On MainActivity, method requestPermissions is necessary to request
permissions on camera.
On manifest

```
<uses-permission android:name="android.permission.CAMERA" />
```

On MainActivityFragment, see method callZXing(), for reading.

On GeneratorActivityFragment, see method encodeAsBitmap for generating QRCode.






