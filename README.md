This is a fork of [NetBeans SVUID Generator](https://github.com/hlavki/netbeans-svuid-generator).

It makes the following changes:

- Uses annotation based generation of the layer.xml for registration and the packaged NBM
  properly registers now.
  
- Automatically applies the recommended @Serial annotation and imports it if needed.

- Updated to NetBeans 31.

Things I plan to add:

- Settings for ID generation strategies. The original documentation says it's there, but it isn't.

- Setting to enable/disable addition of the @Serial annotation

- A new action to 'Make this serializable' on a class declaration which will both implement Serialization
  and add an ID.
  
- When Serialization 2.0 is introduced, we'll see what needs to be done then.

Original README:
# NetBeans Plugin: serialVersionUID Generator

## Overview:
The **serialVersionUID Generator** is NetBeans plugin designed for Java developers who need to work with Serializable classes. This plugin automates the generation of the `serialVersionUID` field, ensuring that your serialized objects maintain compatibility across different versions of your classes.

## Key Features:
- **Automatic serialVersionUID Generation**: Seamlessly generates the `serialVersionUID` field for any class implementing the `Serializable` interface.
- **Customizable ID Calculation**: Choose between constant 1L ID or calculating the ID based on the class's structure, providing flexibility according to your project needs.
- **Error-Free Serialization**: Prevents common serialization-related issues by ensuring each Serializable class has a consistent and unique identifier.
- **Code Consistency**: Helps maintain best practices in Java serialization, ensuring backward compatibility of your objects.

## Benefits:
- **Save Time**: Eliminates the need for manual calculation and insertion of the `serialVersionUID`, speeding up your development process.
- **Reduce Bugs**: Minimize the risk of serialization errors that could arise from missing or inconsistent `serialVersionUID` fields.
- **Improve Code Quality**: Maintain high standards in your codebase by automating this essential aspect of Java development.

## How It Works:
1. **Installation**: Simply install the plugin via the NetBeans Plugin Manager.
2. **Usage**: Right-click on any Serializable class or configure the plugin to automatically add the `serialVersionUID` field as you code or use code hint.
3. **Configuration**: Access the plugin settings to choose between different ID generation strategies or customize the workflow to suit your project's needs.
