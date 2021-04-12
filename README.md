# TheWhite3
Отборочное задание на бесплатное обучение от компании TheWhite на 2021 год 
https://github.com/thewhitesoft/student-2021-assignment#destutroms-secrets

# Решение задачи 
Для начала о моих впечатлениях о поставленной задаче:

1. Нет пояснения, нужно ли выполнять задание под звёздочкой отдельно или вместо основого. 
2. "имена до UUID совпадают (для файлов - должны совпадать расширения). При совпадении отсортировать по имени файла с UUID и взять первый файл или директорию."
Но в то же время в примере "dest/Utrom's secrets" в задача решена так, что взяты файлы и папки не первые по отсортированному UUID, а по наибольшиму размеру файла.
3. Внутри файлов написан размер самих файлов, вместо какого-либо условного "мусора", что очень сильно запутывает. Получается, что я могу сравнить условно "размер", написанный внутри файла вместо того, чтобы сравнивать содержимое для задания под звёздочкой? Алгоритмы там будут отличаться...
4. "Данные одинаковые, если..."? Если принять, чтобы файлы были одинаковы, нужно выполнение всех трёх (если выполнять задание со звёздочкой) условий, в то же время для условия 2 и 3 абсолютно разные действия, что опять-таки приводит к списку п.1

Таким образом было принято решение выполнять задание следующим образом: 

Файлы одинаковы если данные одного типа (оба файла или обе директории); имена и содержимое совпадает. Выполнять условие по манипуляции с папками и файлами исходя из пункта со звёздочкой. 

#Работа с программой

Чтобы запустить, нужно воспользоваться командой "java -jar TheWhite3.jar", в аргументах -src и -dest указать папки с входными и папку для выходных данных. При указании относительных путей необходимо переместить файл TheWhite3.jar в нужную директорию. 
Пример работы: 

```
java -jar TheWhite.jar -scr ".\\srcc\\Utrom's secrets" -dest "D:/folder"
```
Выходные данные:
```
Исходная директория
.\srcc\Utrom's secrets
	|- Research 5e5ce901-b486-49e1-848a-a8134a791374/
		|- finded.secret 75Kb
	|- Research fe2fbaed-9a7f-4f9a-b430-e089a9771c95/
		|- finded.secret 70Kb
	|- Secret data 047f1762-d6f4-4f41-9ee3-9dfacf2575ec/
		|- middle.secret 30Kb
		|- top.secret 500Kb
	|- Secret data cdd8d173-61d9-4eaa-a827-61ebd75ce7da/
		|- middle.secret 30Kb
	|- Share ba8ad6f4-0194-4e0c-b825-78582720bba3/
		|- shared.secret 45Kb
	|- Share e1106238-d0f3-4902-9478-f012b87ce2a8/
		|- shared.secret 45Kb
	|- Total secret 86b5d8c6-5b6c-493f-b626-b7b506900687/
		|- total.secret 17Kb
	|- Escape 5e5ce901-b486-49e1-848a-a8134a791374.plan 56Kb
	|- Win e4e31179-1e60-46d5-a868-fbb709789e07.plan 25Kb
	|- Win f51a1b8a-1519-4ac0-b432-00d6d9001400.plan 256Kb
D:\folder\Utrom's secrets.zip
	|- Research/
		|- finded.secret 75Kb
	|- Research (1)/
		|- finded.secret 70Kb
	|- Secret data/
		|- middle.secret 30Kb
		|- top.secret 500Kb
	|- Secret data (1)/
		|- middle.secret 30Kb
	|- Share/
		|- shared.secret 45Kb
	|- Total secret/
		|- total.secret 17Kb
	|- Escape.plan 56Kb
	|- Win (1).plan 25Kb
	|- Win.plan 256Kb

Process finished with exit code 0


Process finished with exit code 0
```
