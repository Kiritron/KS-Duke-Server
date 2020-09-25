### **КС Дьюк [Сервер]**

##### Версия: 1.1
##### Описание
Сервер для реализации проверки обновлений для приложения.
Необходим для работы SOCKET библиотеки КС Дьюк.

##### Использование
Положите исполняемый файл сервера в отдельную папку. Это необязательно, но рекомендуется, чтобы не создавать мусор.

Запустите сервер через консоль вашей ОС с помощью команды "java -jar _имя_исполняемого_файла_сервера.jar_". Не забудьте указать дополнительные аргументы к запуску, если они требуется. Сервер рядом с собой создаст конфиг формата TOLF(КС Толчок). Настройте порт для этого сервера в этом конфиге. Запустите сервер снова и введите команду _help_, чтобы узнать какие команды вам доступны. При создании профиля имейте в виду, что библиотека будет обращаться к серверу именно по имени профиля приложения. То есть если в библиотеке имя программы указывается как "АЛИСА", то и профиль на сервере должен называться так же с учётом регистра.

#### Приятного пользования!