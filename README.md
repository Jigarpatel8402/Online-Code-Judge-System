# ⚖️ Online Code Judge System

A Java-based CLI application that simulates a competitive programming judge platform. Register, solve problems, get evaluated, and compete on the leaderboard — all from your terminal.

Built as an academic project demonstrating **Object-Oriented Programming** concepts with **JSON-based persistence** using Gson.

---

## 🎯 Features

- **User System** — Register, login, logout with session management
- **Problem Bank** — Browse coding problems with difficulty levels and test cases
- **Simulated Submissions** — Enter outputs for each test case and get instant evaluation
- **Automated Scoring** — Percentage-based scoring via Evaluator interface
- **Leaderboard** — Compete with other users ranked by total score
- **JSON Persistence** — All data saved to `users.json` and `problems.json`
- **Colored CLI** — ANSI-colored prompt that reflects login state

---

## 📂 Project Structure

```
Code_Judge/
├── Main.java                     # CLI controller & entry point
├── Evaluator.java                # Evaluation interface
├── BasicEvaluator.java           # Evaluator implementation
├── ScoreCalculator.java          # Scoring interface
├── DefaultScoreCalculator.java   # Scoring implementation
├── model/
│   ├── User.java                 # User model
│   ├── Problem.java              # Problem model
│   ├── TestCase.java             # TestCase model
│   └── Submission.java           # Submission model
├── jsoncmd/
│   └── JSONUtil.java             # JSON read/write utility
├── lib/
│   └── gson-2.10.1.jar           # Gson library
├── users.json                    # User data storage
└── problems.json                 # Problem data storage
```

---

## 🚀 Getting Started

### Prerequisites

- Java JDK 8 or higher
- Gson library (included in `lib/`)

### Compile & Run

```bash
# Clean old compiled files (PowerShell)
Get-ChildItem -Recurse -Filter "*.class" | Remove-Item -Force

# Compile
javac -cp "lib/gson-2.10.1.jar;." Main.java

# Run
java -cp "lib/gson-2.10.1.jar;." Main
```

> **Note:** On Linux/Mac, replace `;` with `:` in the classpath.

---

## 💻 Commands

| Command | Auth | Description |
|---------|:----:|-------------|
| `help` | — | Show all available commands |
| `newuser <name> <pass>` | — | Register a new user |
| `login <name> <pass>` | — | Login to your account |
| `logout` | ✅ | Logout from current session |
| `who` | ✅ | Show current logged-in user |
| `problems` | — | List all coding problems |
| `problem <id>` | — | View problem details & test cases |
| `submit <id>` | ✅ | Submit solution for a problem |
| `myresults` | ✅ | View your submission scores |
| `leaderboard` | — | View user rankings |
| `result` | ✅ | Show raw results list |
| `allusers <adminpass>` | 🔑 | List all users (admin only) |
| `clear` / `cls` | — | Clear terminal screen |
| `exit` | — | Exit the application |

---

## 📸 Demo

```
== Welcome to Online Code Judge ==
CodeJudge> login jigar 123
Welcome jigar!

CodeJudge/@jigar> problems
== Problem List ==
  ID: 1 | Sum of Two Numbers [Easy]
  ID: 2 | Even or Odd [Easy]
  ID: 3 | Reverse a String [Medium]

CodeJudge/@jigar> submit 1
== Sum of Two Numbers ==
Difficulty: Easy
Test Case 1 - Input: 2 3
Your Output> 5
Test Case 2 - Input: 10 20
Your Output> 30
Test Case 3 - Input: -5 5
Your Output> 0

Result: 3/3 passed (100%)
Submission saved!

CodeJudge/@jigar> leaderboard
== Leaderboard ==
  Rank | Username       | Total Score
  -----|----------------|------------
  1    | jigar          | 100
```

---

## 🧱 OOP Concepts Used

| Concept | Implementation |
|---------|---------------|
| **Encapsulation** | Private fields with public getters in all model classes |
| **Abstraction** | `Evaluator` and `ScoreCalculator` interfaces |
| **Polymorphism** | `BasicEvaluator implements Evaluator` |
| **Composition** | `Problem` has `List<TestCase>`, `User` has `List<Submission>` |
| **Interface** | Pluggable evaluation and scoring strategies |

---

## 📊 How Evaluation Works

1. User runs `submit <id>`
2. System shows each test case input
3. User enters their output for each case
4. `BasicEvaluator` compares outputs with expected values
5. `DefaultScoreCalculator` computes: **score = (passed × 100) / total**
6. Score is saved to user's results and submission history

---

## 🛠️ Technologies

- **Java SE 8+** — Core language
- **Gson 2.10.1** — JSON serialization/deserialization
- **JSON** — Data persistence
- **ANSI Escape Codes** — Colored terminal output

---

## 📄 License

This project is for academic/educational purposes.

---

> Built with ☕ Java
