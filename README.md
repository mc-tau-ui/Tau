# Tau
A powerful and versatile forge UI library
Tau is a UI library for Minecraft Forge that simplifies the process of creating graphical user interfaces (GUIs). It provides a Flutter-like syntax for creating and customizing the appearance of UI elements, making it easy for developers to create attractive, responsive, and functional interfaces.


# Key Features
- **Flutter-like Syntax**: Tau uses a syntax similar to that of Flutter, making it easy for developers familiar with Flutter to get started with Tau.
- **Extensible**: Tau is designed to be extensible, allowing developers to add their own custom UI elements and widgets to the library.
- **Easy To Use**: Tau is designed to be easy to use. I am personally really not a fan of the workflow that goes into making Minecraft UIs. Tau changes that workflow into a more organized and maintainable fashion (In my opinion).


# Capabilities
Currently, there are 2 renderers for Tau. They are built upon existing Minecraft UI renderers. There is one for Screen and another one for Hud. So you can use Tau to build Huds and various different types of screens as you need. There is currently no support for making UI for containers (as of version 1.0.0). But that is something I would like to look into.

# Example
Here are some example code that can be used to set up a simple Tau UI
```java
public class ExampleUI implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Stack(
            new Container.Builder().withColor(Color.WHITE),
            new Center(new Sized(
                Size.staticSize(new Vector2i(100, 20)),
                new TextField.Builder()
                    .withMessage(new TextComponent("Hello"))
                    .withHintText(new TextComponent("Hello!")))
        ));
    }
}
```

in this snippet, it creates a list of UI elements that stack on top of each other. In this case, an empty container filled with the color white, and a text field with the size 100(w) 20(h) centred in the middle of the screen with a narration message of "Hello" and a hint text of "Hello!"

Lastly, This UI can be displayed onto the screen with 
```java
Minecraft.getInstance().setScreen(new ScreenUIRenderer(new TestStatic()));
```

# Getting Started
You can head to the documentation website [here](https://mc-tau-ui.github.io/)
