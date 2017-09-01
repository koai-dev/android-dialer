# android-dialer

[![Download](https://api.bintray.com/packages/dialog/maven/im.dlg%3Aandroid-dialer/images/download.svg)](https://bintray.com/dialog/maven/im.dlg%3Aandroid-dialer/_latestVersion)
[![license](https://img.shields.io/github/license/dialogs/android-dialer.svg)](LICENSE)

A reusable dialer implementation extracted from AOSP.

![Demo](demo.gif?raw=true)

## Including in your project

```
repositories {
    jcenter()
}
compile 'im.dlg:android-dialer:1.1.0'
```

## Usage

### 1) Within fragment

Just add the `DialpadFragment` into your activity and make sure the activity implements
`DialpadActivity.Callback`:

```
interface Callback {
    void ok(String formatted, String raw);
}
```

The formatted string contains the input as it displayed to user (+1 555-546-0001) and the raw
string contains characters only (+15555460001).


### 2) Via `startActivityForResult`

```
Intent intent = new Intent(context, DialpadActivity.class);
startActivityForResult(intent, 100); // any result request code is ok
```

And then in your `onActivityResult`:

```
@Override
override void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == Activity.RESULT_OK) {
        String formatted = data.getStringExtra(DialpadActivity.EXTRA_RESULT_FORMATTED);
        String raw = data.getStringExtra(DialpadActivity.EXTRA_RESULT_RAW);
        ...
    }
}
```

### Configuration

Whether you're using a fragment or an activity, you can provide configuration via extras.
For a fragment use `setArguments`, and for activity use intent extras.

Arguments are:

1) `EXTRA_REGION_CODE` (string): Region code for the phone formatter. Default is `US`.
2) `EXTRA_FORMAT_AS_YOU_TYPE` (boolean): Enable phone formatting. If disabled, both `formatted` and
`raw` results will be the same. Default is `true`.
3) `EXTRA_ENABLE_STAR` (boolean): Will show the 'star' symbol. Default is `true`.
3) `EXTRA_ENABLE_POUND` (boolean): Will show the 'pound' symbol. Default is `true`.
3) `EXTRA_ENABLE_PLUS` (boolean): Will show the 'plus' symbol. Default is `true`.

## License

```
Copyright (c) 2017-present, dialog LLC <info@dlg.im>.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
