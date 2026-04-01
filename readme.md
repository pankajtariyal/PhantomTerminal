# PhantomTerminal

PhantomTerminal is a **custom Java-based terminal emulator** designed to simulate a real command-line environment using a modular command architecture.

The project implements a **Command Pattern-based CLI system** where every terminal command is encapsulated as a separate class.  
It supports **file system operations, calculator commands, and expression evaluation**, making it a flexible and extensible terminal framework.

The system is designed to allow **easy extension of commands without modifying the core engine**.

---

# Features

• Custom Java terminal interface  
• Modular command-based architecture  
• File system operations  
• Calculator commands with high precision arithmetic  
• Expression-based calculator (`cal` command)  
• Tokenized mathematical expression parsing  
• Command registry and parsing engine  
• Extensible command framework

---

# Project Architecture

PhantomTerminal follows a **Command Design Pattern** combined with a **command execution engine**.

```
PhantomTerminal
│
├── commands
│   │
│   ├── Command.java
│   │
│   ├── fileOperationCommand
│   │   ├── FileCommand.java
│   │   ├── CdFileCommand.java
│   │   ├── LsFileCommand.java
│   │   ├── MkdirFileCommand.java
│   │   ├── RmdirFileCommand.java
│   │   ├── TouchFileCommand.java
│   │   ├── CatFileCommand.java
│   │   ├── ClearFileCommand.java
│   │   ├── HelpFileCommand.java
│   │   └── MVFileCommand.java
│   │
│   ├── calculator
│   │   ├── CalculatorCommand.java
│   │   ├── AbstractCalculatorCommand.java
│   │   ├── AddCommand.java
│   │   ├── SubtractCommand.java
│   │   ├── MultiplicationCommand.java
│   │   ├── DivideCommand.java
│   │   └── AllCalculationOperation.java
│   │
│   └── tokenizer
│       └── Tokenize.java
│
├── common
│   │
│   ├── CommonVariable.java
│   │
│   └── calculatorUtil
│       ├── CalculatorUtil.java
│       └── CalculatorUtilImp.java
│
├── engine
│   │
│   ├── CommandEngine.java
│   ├── CommandParser.java
│   └── CommandRegistry.java
│
├── uicontroller
│   │
│   └── TerminalController.java
│
└── Main.java
```

---

# Command System

All terminal commands implement the base interface:

```java
public interface Command {
    void execute(List<String> args) throws IOException;
}
```

This ensures that every command follows the same execution contract.

---

# File System Commands

| Command | Description |
|------|-------------|
| `ls` | List files and directories |
| `cd` | Change directory |
| `mkdir` | Create directory |
| `rmdir` | Remove directory |
| `touch` | Create new file |
| `cat` | Display file contents |
| `mv` | Move or rename files |
| `clear` | Clear terminal output |
| `help` | Display available commands |

---

# Calculator Commands

| Command | Description |
|------|-------------|
| `add` | Add numbers |
| `sub` | Subtract numbers |
| `mul` | Multiply numbers |
| `div` | Divide numbers |
| `cal` | Evaluate mathematical expressions |

---

# Example Usage

## File System Commands

List files:

```
ls
```

Create a directory:

```
mkdir myFolder
```

Change directory:

```
cd myFolder
```

Create a file:

```
touch file.txt
```

Read file contents:

```
cat file.txt
```

Move a file:

```
mv file.txt newFolder/
```

---

# Calculator Usage

Addition

```
add 10 20 30
```

Output

```
60
```

Subtraction

```
sub 20 5
```

Output

```
15
```

Multiplication

```
mul 5 4
```

Output

```
20
```

Division

```
div 10 2
```

Output

```
5
```

---

# Expression Calculator

The `cal` command evaluates full mathematical expressions.

Example:

```
cal 10+2*5
```

Output

```
20
```

Parentheses are supported:

```
cal (10+2)*5
```

---

# Expression Parsing

Expressions are first **tokenized** before evaluation.

Example:

Input:

```
10+2*5
```

Tokens:

```
[10, +, 2, *, 5]
```

---

# Calculation Engine

The expression evaluator uses a **two-stack algorithm**.

```
Stack<BigDecimal> numbers
Stack<String> operators
```

Steps:

1. Parse tokens
2. Push numbers to number stack
3. Push operators to operator stack
4. Apply precedence rules
5. Execute operations
6. Return final result

---

# Precision Handling

All calculations use:

```
java.math.BigDecimal
```

This prevents floating point precision errors.

Example:

```
0.1 + 0.2 = 0.3
```

---

# Command Execution Flow

User Input

```
user command
```

Processing flow:

```
User Input
   ↓
CommandParser
   ↓
CommandRegistry
   ↓
CommandEngine
   ↓
Execute Command
```

This separation allows commands to be **added without modifying the core engine**.

---

# Extending PhantomTerminal

New commands can easily be added.

Example:

```java
public class PowerCommand extends AbstractCalculatorCommand {

    @Override
    protected String getOperator() {
        return "^";
    }

    @Override
    protected String getCommandName() {
        return "pow";
    }
}
```

Then register the command in `CommandRegistry`.

---

# Requirements

Java 8 or higher

---

# Future Improvements

Planned enhancements:

• Command auto-completion  
• Plugin system  
• Script execution support  
• Networking commands  
• Voice command support  
• AI terminal assistant

---

# Author

Abhishek Tadiwal

Developer of PhantomTerminal

---

# License

MIT License
