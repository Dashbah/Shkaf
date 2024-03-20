# Shkaf - Руководство по запуску приложения

Это руководство поможет вам запустить приложение на вашем компьютере.

## Подготовка базы данных

Перед запуском приложения необходимо убедиться, что база данных доступна. Можно использовать пустую базу данных или создать новую.

Для создания и запуска базы данных в контейнере Docker, выполните следующие команды:

```bash
docker-compose up -d db
```

Эта команда создаст и запустит контейнер с базой данных.
То есть для запуска можно не создавать у себя бд, просто довериться контейнеру

## Запуск приложения

Перед выполнением нужно войти в папку Shkaf (содержащую Dockerfile)

```bash
# Переход по папкам
cd PapkaName
# Просмотр содержимого
ls
```
Для запуска приложения выполните следующие команды:

```bash
docker-compose build
docker-compose up
```

```bash
# В фоновом режиме вроде можно запустить так
docker-compose build
docker-compose up -d
# Тогда останавливаем такой командой
docker-compose down
```

Это запустит приложение в контейнере Docker.

## Остановка приложения

Чтобы остановить выполнение приложения в терминале, используйте комбинацию клавиш CTRL+C. Это прервёт выполнение приложения и остановит его работу.

```bash
# Остановка выполнения приложения в терминале
CTRL+C
# При запуске в фоновом режиме
docker-compose down
```

Это приведет к выходу из запущенных процессов и прекращению работы приложения.


## Если что-то пошло не так

1) Стоит собрать все логи и рассказать о них
2) Можно удалить image в дашборде докера и собрать приложение еще раз