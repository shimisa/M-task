## Developer Guide: How to Add a New Action

To create a custom line-manipulation action for this infrastructure:

1. Create a new class that implements `com.example.textprocessor.Action`.

```java
public class MyCustomAction implements Action {
    @Override
    public List<String> perform(List<String> lines) {
        // Your custom logic
    }

    @Override
    public String toString() {
        return "mycustom"; // Name used from command line
    }
}

```


2. Register it in the file `META-INF/services/org.example.textprocessor.Action` in the resources directory. This file should contain the fully qualified name of your new class.

   Add your new class:  `org.example.textprocessor.MyCustomAction`


#### That’s it! Run your action like this:

1. Build the project using Maven:

```bash

mvn clean package

```

2. Then run the jar with your custom action:

```bash

java -jar target/text-processor.jar input.txt output.txt mycustom

```