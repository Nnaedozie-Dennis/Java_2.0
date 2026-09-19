### Introduction

This program demonstrates the basic Java Thread model by creating a simple clock that continuously displays the current time and date. Two separate threads are used for the clock operation. One thread updates the current time in the background, while another thread displays the updated time on the console.

The display thread is assigned a higher priority than the background update thread. This demonstrates how Java thread priorities can be used when different tasks have different levels of importance.

The program also uses `volatile` for the shared time value. This allows changes made by the updating thread to be visible to the display thread without creating unnecessary synchronization problems.

### Program Structure

The program contains one `Clock` class with methods responsible for updating and displaying the time. The `main` method creates two threads:

1. The update thread continuously obtains the current date and time.
2. The display thread continuously prints the latest time to the console.

The display thread has a higher priority than the update thread.



### Explanation of the Program

The `Clock` class contains a `currentTime` variable that stores the latest formatted time. It is declared with the `volatile` keyword because the value is shared between two threads.

The `updateTime()` method obtains the current date and time using:

```java
LocalDateTime.now()
```

The `DateTimeFormatter` formats the value as:

```text
HH:mm:ss dd-MM-yyyy
```

For example:

```text
14:35:27 19-09-2026
```

The update thread refreshes the time every 500 milliseconds.

The `displayTime()` method runs separately and prints the most recent value every second. This demonstrates concurrent execution because the updating and displaying operations are handled by different threads.

### Thread Priorities

The program creates two threads:

```java
Thread updateThread
Thread displayThread
```

The update thread receives normal priority:

```java
updateThread.setPriority(Thread.NORM_PRIORITY);
```

The display thread receives the maximum Java thread priority:

```java
displayThread.setPriority(Thread.MAX_PRIORITY);
```

Therefore, the display thread has a higher priority than the background update thread, satisfying the assignment requirement.

The priority values are also displayed when the program starts. In the standard Java implementation, the normal priority is `5` and the maximum priority is `10`.

Thread priority should be understood as a scheduling preference rather than a guarantee. The operating system and Java runtime ultimately determine how processor time is allocated.

### Error Handling

The program handles `InterruptedException` when the threads are sleeping.

When an interruption occurs, the program restores the thread's interrupted status with:

```java
Thread.currentThread().interrupt();
```

This provides a clean way for the main method to stop the two clock threads after the demonstration period.

### Program Flow

The program follows this sequence:

```text
Create Clock object
       ↓
Create update thread
       ↓
Create display thread
       ↓
Assign thread priorities
       ↓
Start both threads
       ↓
Update thread obtains current time
       ↓
Display thread prints current time
       ↓
Threads continue running concurrently
       ↓
Main thread waits 15 seconds
       ↓
Threads are interrupted
       ↓
Clock application stops
```

### Expected Output

The exact time will depend on when the program is executed, but the output should look similar to:

```text
===== SIMPLE CLOCK APPLICATION =====
Update Thread Priority: 5
Display Thread Priority: 10
Clock is running...

Current Time: 10:25:31 19-09-2026
Current Time: 10:25:32 19-09-2026
Current Time: 10:25:33 19-09-2026
Current Time: 10:25:34 19-09-2026
Current Time: 10:25:35 19-09-2026
Current Time: 10:25:36 19-09-2026
Current Time: 10:25:37 19-09-2026
Current Time: 10:25:38 19-09-2026
Current Time: 10:25:39 19-09-2026
Current Time: 10:25:40 19-09-2026
Current Time: 10:25:41 19-09-2026
Current Time: 10:25:42 19-09-2026
Current Time: 10:25:43 19-09-2026
Current Time: 10:25:44 19-09-2026
Current Time: 10:25:45 19-09-2026

Clock application stopped.
```

The displayed date and time will naturally be different when the program is run.

### Conclusion

The application demonstrates how Java threads can be used to perform separate tasks concurrently. The update thread maintains the latest time while the display thread prints that information independently. Assigning a higher priority to the display thread demonstrates Java's thread priority mechanism. The use of interruption also allows the application to stop the threads cleanly rather than terminating them abruptly.

This structure demonstrates the main concepts from the unit, including thread creation, concurrent execution, thread priorities, sleeping, interruption, and communication between threads through shared data.
