# Bank_2
_autor Vladimir Prilepskiy 2016_

Программа предназначена для импортирования платежных поручений в ОЦ Petrol Plus v.5.9.1.0 (процессинг пластиковых смарт-карт) из файла экспортированного из клиент банка для 1С.
1. Кнопка Open File
При открытии файла происходит его построчное считывание. При считывании каждой строки, ищется совпадение начала строки с искомым полем и запись в переменную значения за найденным полем.
Найденные значения записываются в двумерную коллекцию, затем передаются в JTable для визуального отображения, найденых в файле, значений.
2. Кнопка Export OC 
Происходит чтение коллекции с обращением с БД Oracle (jdbc) и запись в новую коллекцию с извлеченными данными из БД и последующим отображением в JTable.
Создается файл для экспорта в Petrol Plus.
3. Кнопка Export PC 
ПроиПроисходит чтение коллекции с обращением с БД Oracle (jdbc) и запись в новую коллекцию с извлеченными данными из БД и последующим отображением в JTable.

