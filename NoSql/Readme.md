Task 2. (30 points + 10 bonus points) Task Manager with Mongo DB

Install MongoDB and use corresponding java driver.
Create simple task manager console app. Your tasks should have next field: date of creation, dead line, name, description, list of subtasks with simple structure (name/description), and category.

Provide next operation:

1.	display on console all tasks
2.	display overdue tasks
3.	display all tasks with the specific category (query parameter)
4.	display all subtasks related to tasks with the specific category (query parameter)
5.	perform insert/update/delete of the task
6.	perform insert/update/delete all subtasks of the given task (query parameter)
7.	support full-text search by word in task description
8.	support full-text search by sub-task name

For highest mark, you can try implement DAO with any ORM solution for Mongo (+ 10 points)
