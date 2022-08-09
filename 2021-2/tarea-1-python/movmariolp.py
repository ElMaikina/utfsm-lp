import re

"""
init_matrix (Iniciar Matríz)

Parametro 1 : entero side

A partir de un entero "side" se genera una matriz
cuadrada en la cual se operará más adelante
"""


def init_matrix(side):
    final_matrix = []
    if side > 0:
        for i in range(side):
            matrix_aux = []
            for j in range(side):
                matrix_aux.append(0)
            final_matrix.append(matrix_aux)
    return final_matrix


"""
process (Procesar Elemento)

Parametro 1 : string elemento
Parametro 2 : arreglo string array
Parametro 3 : entero pos

Toma un arreglo de strings y añade el string 
"elemento" en la posición "pos".
Si la posición "pos" del arreglo está libre, 
entonces se remplaza. Si está ocupada, se 
concatena con un string auxiliar
"""


def process(elem, array, pos):
    if array[pos] == '':
        array[pos] = elem
        return
    aux_string = array[pos] + elem
    array[pos] = aux_string
    return


"""
parentesis (Ordenar por Paréntesis)

Parametro 1 : string text

Busca las expresiones regulares entre parentesis.
Dependiendo de la estructura de las RE halladas,
las ordenará a través de un sistema de prioridad
en un arreglo de strings.
Cada vez que haye un "(" sube la prioridad del 
elemento y baja cada ")", provocando que los
elementos que vayan primero estén dentro de más
paréntesis.
Aquellos que tengan prioridades iguales se 
concatenarán a través de la función "process"
"""


def parenthesis(text):
    check_open = re.compile(r"\(")
    check_close = re.compile(r"\)")
    open_find = check_open.findall(text)
    close_find = check_close.findall(text)

    if len(open_find) == 0 or len(close_find) == 0:
        return text

    elif len(open_find) != len(close_find):
        return ''

    height = len(open_find) + 1

    p = re.compile(r"\(*[ABXYUD<>0-9]+\)*")
    p_find = p.findall(text)
    final_array = []

    for i in range(height):
        final_array.append('')

    height = -1

    for match in p_find:
        if match[0] != "(" and match[-1] != ")":
            process(match, final_array, height)
        if match[0] == "(" and match[-1] == ")":
            height -= 1
            process(match, final_array, height)
            height += 1
        if match[0] == "(" and match[-1] != ")":
            height -= 1
            process(match, final_array, height)
        if match[0] != "(" and match[-1] == ")":
            process(match, final_array, height)
            height += 1

    final_string = ''.join(final_array)
    return final_string


"""
check_question (Chequear Signos de Interrogación)

Parametro 1 : string l_string
Parametro 2 : arreglo i_array
Parametro 3 : entero side
Parametro 4 : entero ix
Parametro 5 : entero iy

Busca a través del formato pedido, las ocurrencias
de un signo de interrogacion y sus respectivas
operaciones dentro de un string.
En el caso de hallar un signo pero donde su comando
no aplique, este se borraá del string (y todas las
instancias donde ese mismo comando aparezca)
Devuelve un string filtrado por las operaciones que
aplican.
"""


def check_question(l_string, i_array, side, ix, iy):
    command = r"\?[<|>|U|D]{1}[A|B|X|Y|R|L|Z|Se|Sc|Le|Lc]{1}[<|>|U|D]?"
    check_q = re.compile(command)
    q_find = check_q.findall(l_string)

    nb_x = 0
    nb_y = 0

    result = l_string

    if len(q_find) == 0:
        return l_string

    if len(q_find) > 0:
        for q_mark in q_find:
            if q_mark[1] == "<":
                nb_x = ix-1
            if q_mark[1] == ">":
                nb_x = ix+1
            if q_mark[1] == "U":
                nb_y = iy-1
            if q_mark[1] == "D":
                nb_y = iy+1
            nb_x = nb_x % side
            nb_y = nb_x % side

            if i_array[nb_y][nb_x] <= i_array[iy][ix]:
                string_aux = str(q_mark)
                result = result.replace(string_aux, '')
    return result


"""
Este es el equivalente a la función main, aqui se abre finalmente
el archivo y se realizara la busqueda de Expresiones Regulares
"""

with open('codigo.txt', 'r') as inputText:
    line = inputText.readline()
    square_side = int(line)
    matrix = init_matrix(int(square_side))

    limit = int(line)
    x = 0
    y = 0

    """
    SNES son todos los comandos dentro de una misma Expresión
    Regular, por ende se compila menos veces que si se buscaran
    los comandos por separado (Se llama así ya que los comandos
    justo coinciden con los botones de Super Nintendo jeje)
    """
    snes = r"A+|B+|R+|Z+|[X|Y]{1}[<|>|U|D]{1}|[<|>|U|D]{1}-?\d+|Le{1}|Lc{1}|Se{1}|Sc{1}"

    """
    find_error es una Expresión Regular que filtra todo lo que
    no caiga dentro de los comandos solicitados
    """
    find_error = r"[^A|^B^|^R|^Z|^X|^Y|^<|^>|^U|^D|Le|Lc|Se|Sc|^\-|^\(|^\)|\d+{1}|\n|\?]+"
    compile_snes = re.compile(snes)
    compile_errors = re.compile(find_error)

    line = inputText.readlines()

    """
    Boolenaos que determinarán el output de errores.txt
    """
    all_lines_right = True
    no_right_lines = True

    errores = open("errores.txt", "w")

    line_number = 0

    for read_lines in line:
        """
        Ambas lineas chequean por errores de formato, la
        primera reordena a traves de los parentesis.
        La segunda busca los signos de interrogacion.
        """
        string = parenthesis(read_lines)
        string = check_question(string, matrix, limit, x, y)
        if string != '':
            find = compile_snes.findall(string)
            error_found = compile_errors.findall(string)
            no_right_lines = False

            if len(error_found) > 0:

                errores.write(str(line_number) + ' ' + read_lines)
                all_lines_right = False

            if len(error_found) == 0:

                for element in find:

                    if element[0] == "A":
                        matrix[y][x] += len(element)

                    if element[0] == "B":
                        matrix[y][x] -= len(element)

                    if element[0] == "R":
                        matrix[y][x] = 0

                    if element[0] == "Z":
                        matrix = init_matrix(int(square_side))

                    if element[0] == ">" or element[0] == "<":
                        x += int(element[1:])
                        x = x % square_side

                    if element[0] == "D" or element[0] == "U":
                        y += int(element[1:])
                        y = y % square_side

                    if element[0] == "X" and 0 < x < square_side and 0 < y < square_side:

                        if element[1] == "<":
                            matrix[y][x] *= matrix[y][x-1]

                        if element[1] == "U":
                            matrix[y][x] *= matrix[y-1][x]

                        if element[1] == ">":
                            matrix[y][x] *= matrix[y][x+1]

                        if element[1] == "D":
                            matrix[y][x] *= matrix[y+1][x]

                    if element[0] == "Y" and 0 < x < square_side and 0 < y < square_side:

                        if element[1] == "<" and matrix[y][x - 1] != 0:
                            matrix[y][x] *= matrix[y][x - 1]

                        if element[1] == "U" and matrix[y - 1][x] != 0:
                            matrix[y][x] *= matrix[y - 1][x]

                        if element[1] == ">" and matrix[y][x + 1] != 0:
                            matrix[y][x] *= matrix[y][x + 1]

                        if element[1] == "D" and matrix[y + 1][x] != 0:
                            matrix[y][x] *= matrix[y + 1][x]

                    if element == "Le":
                        print(matrix[y][x])

                    if element == "Lc" and 32 <= matrix[y][x] < 127:
                        print(chr(matrix[y][x]))

                    if element == "Se":
                        for a in range(int(square_side)):
                            for b in range(int(square_side)):
                                print(matrix[b][a], end='')

                    if element == "Sc":
                        for a in range(int(square_side)):
                            for b in range(int(square_side)):
                                if 32 <= matrix[b][a] <= 127:
                                    print(chr(matrix[b][a]), end='')

        if string == '':
            errores.write(str(line_number)+' '+read_lines)
            all_lines_right = False

        line_number += 1

    if all_lines_right is True and no_right_lines is False:
        print("No hay errores!")
        errores.write("No hay errores!")

    if all_lines_right is False and no_right_lines is True:
        print("No hay lineas correctas :c")
        errores.write("No hay lineas correctas :c")

    errores.close()
