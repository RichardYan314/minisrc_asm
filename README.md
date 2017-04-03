## Synopsis

This project is an assembler for MiniSRC archetecture instruction set. It is created for the course ELEC-374 at ECE department, Queen's University at Kingston.

## Motivation

In the course ELEC-374, students are required to implement and test a MiniSRC processor. For the purpose of testing, students need to write assembly code and manually assemble them into machine code. Such process is tedious and error-prone.

To assist this process, this assembler has been developed. Further customization and extension has been made possible for those who are ambitious and want to seek challenge and bonus marks.

With consideration of the divergence of OS/environment of the students in the department, the assembler has been written in java for portability.

## Installation

Runnable .jar file can be downloaded here (Will be soon updated).

The assembler is compiled with java 1.8.0_66. However, given the java language features used, any version above 1.7 might also work (untested).

## Usage

Assuming assembly code is in `test.s` file under current directory. Run the assembler from command line as follows:

``` bash
java -jar minisrc.jar -i test.s
```

Run the following command for help:

``` bash
java -jar minisrc.jar -h
```

## Tests

Test suit has been prepared with JUnit (4.12, included). Run it in anyway way as you like.

## Contributors

As the assembler now meets the minimal requirement of the course, this project is unlikely to continue. I will occationally check pull request, probablly.

If you want to customize the assembler for your own purpose, please fork this project and do so by all means. But I request should you want to publish it, please clearly indicate your modification no longer suit the requirement for the course ELEC-374 as mentioned above; and also include a reference to the original project (here). I do not want to confuse the students when they search for this assembler on Google (or any other source) and unawaringly get hold on something unsuitable for their purpose.

## About Customization

The assembly syntax is specified in extended Backus¨CNaur form (EBNF), which is where the customizability comes from. Currently, the syntax is still pretty much context-free. For example, it does not allow the using of lables, which is very common in other assembly language.

The syntax specification is parsed with [Coco/R](http://ssw.jku.at/Coco/). It allows the use of semantic mechanism, which can further extend the capability of the assembler.

## License

See the [LICENSE](LICENSE.md) file for license rights and limitations (MIT).