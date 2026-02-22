# Fei User Guide

![Fei UI](Ui.png)

Fei is a task management chatbot that helps you keep track of todos, deadlines, and events.
It provides a simple interface to organize tasks efficiently and automatically saves your tasks for future use.

---

## Information

- Tasks are saved automatically and loaded when Fei starts.
- Dates can be entered in `YYYY-MM-DD` format if you would like them stored in that format. Otherwise, the date will be stored as a normal string.

---

## Supported Commands

### 1. Add a Todo
Adds a simple task.

```
todo <<DESCRIPTION>>
```

---

### 2. Add a Deadline
Adds a task with a deadline.

```
deadline <<DESCRIPTION>> /by <<DATE>>
```

---

### 3. Add an Event
Adds an event with a start and end time.

```
event <<DESCRIPTION>> /from <<START_DATE>> /to <<END_DATE>>
```

---

### 4. List All Tasks
Displays all stored tasks.

```
list
```

---

### 5. Mark a Task as Done
Marks a task as completed.

```
mark <<TASK_NUMBER>>
```

---

### 6. Unmark a Task
Marks a task as not completed.

```
unmark <<TASK_NUMBER>>
```

---

### 7. Delete a Task
Removes a task from the list.

```
delete <<TASK_NUMBER>>
```

---

### 8. Find Tasks
Finds tasks containing one or more keywords. Multiple keywords separated by spaces are treated as **OR** conditions.

```
find <<KEYWORD1>> <<KEYWORD2>> ...
```

---

### 9. Exit the Application
Closes Fei.

```
bye
```

---

## Notes

- `<<TASK_NUMBER>>` refers to the index number shown in the task list.
- Commands are case-sensitive and must follow the specified format.
- Ensure proper spacing when entering commands.

---

Fei is designed to be lightweight, intuitive, and efficient for managing your daily tasks.