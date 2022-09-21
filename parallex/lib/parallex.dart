import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

class parallex extends StatefulWidget {
  const parallex({super.key});

  @override
  State<parallex> createState() => _parallexState();
}

class _parallexState extends State<parallex> {
  late ScrollController main_scroll;

  @override
  void initState() {
    main_scroll = ScrollController()
      ..addListener(() {
        setscroll();
      });
    super.initState();
  }

  double offset = 0;

  void setscroll() {
    setState(() {
      offset = main_scroll.offset;
      print(offset);
    });
  }

  @override
  Widget build(BuildContext context) {
    var size = MediaQuery.of(context).size;
    var eigt = size.height * 3;

    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
            gradient: LinearGradient(
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
                colors: [
              Color.fromARGB(255, 0, 200, 100),
              Color.fromARGB(255, 255, 255, 255)
            ])),
        child: Stack(children: [
          Positioned(
              bottom: offset * 0.3 - 10,
              right: 0,
              left: -5,
              child: SvgPicture.asset('asstes/mountains-layer-4.svg')),
          Positioned(
              bottom: offset * 0.5 - 30,
              right: 0,
              left: 0,
              child: SvgPicture.asset('asstes/mountains-layer-2.svg')),
          Positioned(
              bottom: offset * 0.8 - 55,
              right: 0,
              left: 0,
              child: SvgPicture.asset('asstes/trees-layer-2.svg')),
          Positioned(
              bottom: offset * 1 - 80,
              right: 0,
              left: 0,
              child: SvgPicture.asset('asstes/layer-1.svg')),
          Positioned(
            top: size.height + (offset * -1 * 1),
            right: 0,
            left: 0,
            height: size.height * 2,
            child: Container(
              color: Colors.black,
            ),
          ),
          Positioned.fill(
              child: SingleChildScrollView(
            controller: main_scroll,
            child: SizedBox(height: eigt),
          )),
        ]),
      ),
    );
  }
}
