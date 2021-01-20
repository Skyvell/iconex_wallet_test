import 'package:flutter/services.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

/// A sample app for launching intents.
class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

/// Holds the different intent widgets.
class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Container(
          height: 70,
          color: Colors.black,
          child: RaisedButton(
            child: Text('test'),
            onPressed: getAddress,
          ),
        ),
      ),
    );
  }
}

Future<void> getAddress() async {
  //Om android denna kanal.
  //Om ios annan kanal.
  var platform = const MethodChannel('iconex');
  try {
    final result = await platform.invokeMethod('getAddress');
    print(result);
  } on PlatformException catch (e) {
    print(e);
  }
}
