import re

# Patrones que se usaran para buscar los RE
# Calle: Tiene que empezar si o si con una mayúzcula
#       depués contiene números letras, espacios y
#       guiones.
#
# ID: Debe empezar con un #, luego debe seguir con dos 
#   mayúsculas y finalizar con dos números.
#
# Nombre: Uno o más nombres, donde el primer caracter
#       debe ir en mayúscula, luego caracteres en
#       minúscula, guines o espacios
#
# Apellido: Uno o más nombres, donde el primer caracter
#       debe ir en mayúscula, luego caracteres en
#       minúscula, guines o espacios
#
# RUT: El primer número del rut siempre es entre 1 y 9
#   luego los siguientes números del rut pueden ser entre
#   entre 0 y 9, finalmente, el digito verficaor puede
#   ser entre 0, 9 o un guíon

# Aqui van las expresiones regulares atómicas
nombre_calle = r'[A-Z]{1}[a-zA-Z0-9_]*'
id_calle = r'#[A-Z]{2}[0-9]{2}'
nombre = r'[A-Z]{1}[a-zA-Z ,]+'
apellido = r'[A-Z]{1}[a-zA-Z ,]+'
telefono = r'\+[1-9]{1}[0-9]*'
rut = r'[1-9]{1}[0-9]*\-[0-9|k]{1}'

# Aquí van las expresiones regulares compuestas
persona = nombre + r'\_' + apellido + r'\_' + telefono + r'\_' + rut
calle = nombre_calle + r'\.' + id_calle + r'\.' + persona
camino = r'[(]' + calle + r'[:]' + calle + r'[)]'
sub_camino = calle + r'[:]' + calle

# Finalmente, aqui van los patrones que se deben buscar
# para realizar el chequeo pedido
re_calle = calle 
re_camino = camino 
re_camino_alt = r'\(' + id_calle + r':' + id_calle + r'\)'
re_print = r'print\ ' + id_calle 
re_print_all = r'print_all'
re_print_caminos = r'print_caminos\ ' + id_calle
re_print_by_nombre = r'print_by_nombre\ ' + nombre
re_print_by_apellido = r'print_by_apellido\ ' + apellido
re_print_by_rut = r'print_by_rut ' + rut
re_print_by_telefono = r'print_by_telefono\ ' + telefono
re_update = r'update\ ' + id_calle + r'\ ' + calle
re_valid_camino = r'valid_camino\ ' + id_calle + r'\ ' + id_calle

# Posteriormente, todas las expresiones compiladas
# para poder hacer distintos tipos de Matches
p_calle = re.compile(re_calle)
p_camino = re.compile(re_camino)
p_camino_alt = re.compile(re_camino_alt)
p_print = re.compile(re_print)
p_print_all = re.compile(re_print_all) 
p_print_caminos = re.compile(re_print_caminos)
p_print_by_nombre = re.compile(re_print_by_nombre)
p_print_by_apellido = re.compile(re_print_by_apellido)
p_print_by_rut = re.compile(re_print_by_rut) 
p_print_by_telefono = re.compile(re_print_by_telefono)
p_update = re.compile(re_update)
p_valid_camino = re.compile(re_valid_camino)

p_nombre_calle = re.compile(nombre_calle)
p_id_calle = re.compile(id_calle)
p_nombre = re.compile(nombre)
p_apellido = re.compile(apellido)
p_telefono = re.compile(telefono)
p_rut = re.compile(rut)

# Definicion del Nodo
class Nodo:
   
    # Nombre de la Función: __init__
    # A partir de un string, inicializa una instancia
    # de sí mísmo. Usé una sola variable para nombre
    # y apellido ya que tienen la misma expresión

    def __init__(self, string):
        nombre_calles = re.findall(nombre_calle, string)
        id_calles = re.findall(id_calle, string)
        nombre_apellido = re.findall(nombre, string)
        telefonos = re.findall(telefono, string)
        ruts = re.findall(rut, string)

        self.nombre_calle = nombre_calles[0]
        self.id_calle = id_calles[0]
        self.nombre = nombre_apellido[-2]
        self.apellido = nombre_apellido[-1] 
        self.telefono = telefonos[0]
        self.rut = ruts[0]
    
    # Nombre de la Función: get_nombre_calle
    # Parámetros: Ninguno
    # Devuelve el nombre de la calle 

    def get_nombre_calle(self):
        return self.nombre_calle 

    # Nombre de la Función: get_id
    # Parámetros: Ninguno
    # Devuelve el id de la calle
    
    def get_id(self):
        return self.id_calle 

    # Nombre de la Función: get_nombre
    # Parámetros: Ninguno
    # Devuelve el nombre de la persona
    
    def get_nombre(self):
        return self.nombre 

    # Nombre de la Función: get_nombre
    # Parámetros: Ninguno
    # Devuelve el apellido de la persona
    
    def get_apellido(self):
        return self.apellido 

    # Nombre de la Función: get_telefono
    # Parámetros: Ninguno
    # Devuelve el telefono de la persona

    def get_telefono(self):
        return self.telefono 

    # Nombre de la Función: get_rut
    # Parámetros: Ninguno
    # Devuelve el RUT de la persona
    
    def get_rut(self):
        return self.rut 

    # Nombre de la Función: set_nombre_calle
    # Parámetros: Ninguno
    # Devuelve el nombre de la calle 

    def set_nombre_calle(self, nc):
        self.nombre_calle = nc

    # Nombre de la Función: set_id
    # Parámetros: Ninguno
    # Devuelve el id de la calle
    
    def set_id(self, idc):
        self.id_calle = idc 

    # Nombre de la Función: set_nombre
    # Parámetros: Ninguno
    # Devuelve el nombre de la persona
    
    def set_nombre(self, n):
        self.nombre = n

    # Nombre de la Función: set_nombre
    # Parámetros: Ninguno
    # Devuelve el apellido de la persona
    
    def set_apellido(self, a):
        self.apellido = a

    # Nombre de la Función: set_telefono
    # Parámetros: Ninguno
    # Devuelve el telefono de la persona

    def set_telefono(self, t):
        self.telefono = t

    # Nombre de la Función: get_rut
    # Parámetros: Ninguno
    # Devuelve el RUT de la persona
    
    def set_rut(self, r):
        self.rut = r

    # Nombre de la Función: print
    # Parámetros: Ninguno
    # Muestra por pantalla todos los atributos
    # de la instancia del Nodo en cuestión
    
    def print(self):
        print(self.nombre_calle)
        print(self.id_calle)
        print(self.nombre)
        print(self.apellido)
        print(self.telefono)
        print(self.rut)

with open('input.txt', 'r') as inputText:
    
    input_text = inputText.readline()
    command_list = input_text.split(';')
    errores = open('errores.txt', 'w')
   
    # Si no se genera ningun tipo de comando, el texto
    # completo es inválido
    if not command_list:
        print('El texto no sigue el formato solicitado')
        errores.write(input_text)
   
    else:
        # Las calles se guardarán en un arreglo de Nodos
        # Los caminos se guardarán en un arreglo de Strings
        array_calles = []
        array_caminos = []
        
        # El programa leerá los comandos hallados desde el
        # String generado por el split que se le hizo al input
        for command in command_list:
           
            # Si el comando calza con el patrón de calle
            if p_calle.match(command):
                new_calle = Nodo(command)
                new_id = new_calle.get_id()
                array_calles.append(new_calle)

            # Si el comando calza con el patrón de camino
            # Notar que es el patrón que consiste en (calle:calle)
            elif p_camino.match(command):
                subcommand = re.findall(re_calle, command) # Busca ambas calles
                new_calle_1 = Nodo(subcommand[0]) # Las asigna a dos variables
                new_calle_2 = Nodo(subcommand[1])
                new_id_1 = new_calle_1.get_id() # Genera una calle para cada una
                new_id_2 = new_calle_2.get_id()
                array_calles.append(new_calle_1) # Añade las calles al arreglo de calles
                array_calles.append(new_calle_2)
                
                # Añade los caminos al arreglo de caminos
                array_caminos.append(new_id_1 + '->' + new_id_2) 
                array_caminos.append(new_id_2 + '->' + new_id_1)
            
            # Si el comando calza con el patrón de camino alternativo
            # Notar que es el patrón que consiste en (id_calle:id_calle)
            elif p_camino_alt.match(command):
                ids = re.findall(id_calle, command) # Busca ambos id's del comando
                new_id_1 = ids[0] # Las asigna a dos variables
                new_id_2 = ids[1]
                
                # Luego los agrega al arreglo, asumiendo que son vice-versa
                array_caminos.append(new_id_1 + '->' + new_id_2) 
                array_caminos.append(new_id_2 + '->' + new_id_1) 
            
            # Si el comando calza con el patrón de print id_calle
            elif p_print.match(command):
                target = re.findall(id_calle, command)
                print('CALLE:')
                # Busca en el arreglo de calles algún elemento que calze con el id_calle
                for calle in array_calles:
                    if calle.get_id() == target[0]:
                        calle.print()
            
            # Si el comando calza con el patrón de print_all
            elif p_print_all.match(command):
                # Imprime todos los elementos del arreglo armado hasta el momento
                for calle in array_calles:
                    calle.print()

            # Si el comando calza con el patrón de print_caminos id_calle 
            elif p_print_caminos.match(command):
                target = re.findall(id_calle, command)
                print('CAMINOS DESDE', target[0])

                # Busca todos los caminos que partan desde el id_calle
                # designado por el comando. No hallará los que terminen
                # en id_calle
                for camino in array_caminos:
                    caminos_find = camino.split('->')
                    if caminos_find[0] == target[0]:
                        print(camino)
            
            # Si el comando calza con el patrón de print_by_nombre nombre
            elif p_print_by_nombre.match(command):
                target = re.findall(nombre, command)
                print('CALLES CON PERSONAS DE NOMBRE', target[0])

                # Imprimirá todas las calles en las cuáles calze el
                # nombre buscado en cuestión. Puede haber más de uno
                for calle in array_calles:
                    nombres = calle.get_nombre().split(',')
                    for n in nombres:
                        if n == target[0]:
                            calle.print()

            # Si el comando calza con el patrón de print_by_apellido apellido
            elif p_print_by_apellido.match(command):
                target = re.findall(apellido, command)
                print('CALLES CON PERSONAS DE APELLIDO', target[0])

                # Imprimirá todas las calles en las cuáles calze el
                # apellido buscado en cuestión. Puede haber más de uno
                for calle in array_calles:
                    apellidos = calle.get_apellido().split(',')
                    for a in apellidos:
                        if a == target[0]:
                            calle.print()
            
            # Si el comando calza con el patrón de print_by_rut rut
            elif p_print_by_rut.match(command):
                target = re.findall(rut, command)
                print('CALLES CON PERSONAS DE RUT', target[0])

                # Imprimirá todas las calles en las cuáles calze el
                # rut buscado en cuestión
                for calle in array_calles:
                    if calle.get_rut() == target[0]:
                        calle.print()
            
            # Si el comando calza con el patrón de print_by_telefono telefono
            elif p_print_by_telefono.match(command):
                target = re.findall(telefono, command)
                print('CALLES CON PERSONAS DE TELEFONO', target[0])

                # Imprimirá todas las calles en las cuáles calze el
                # telefono buscado en cuestión
                for calle in array_calles:
                    if calle.get_telefono() == target[0]:
                        calle.print()
            
            # Si el comando calza con el patrón de update id_calle calle
            elif p_update.match(command):
                target = re.findall(id_calle, command) # id a cambiar
                update = re.findall(re_calle, command) # nueva info de la calle
                updated = update[0] # la calle nueva, en formato de string

                updated_nc = re.findall(nombre_calle, updated) # Nuevo nombre_calle
                updated_idc = re.findall(id_calle, updated) # Nuevo id_calle
                updated_n = re.findall(nombre, updated) # Nuevo nombre
                updated_a = re.findall(apellido, updated) # Nuevo apellido
                updated_t = re.findall(telefono, updated) # Nuevo telefono
                updated_r = re.findall(rut, updated) # Nuevo rut

                # Buscará la calle con dicho id_calle
                for calle in array_calles:
                    # Si calza, le asinará todos los valores nuevos
                    if calle.get_id() == target[0]:
                        calle.set_nombre_calle(updated_nc[0])
                        calle.set_id(updated_idc[-1]) # Usa el último valor ya que choca con el otro id
                        calle.set_nombre(updated_n[-2]) # Usa el penúltimo valor ya que choca con nombre_calle
                        calle.set_apellido(updated_a[-1]) # Usa el último valor ya que choca con nombre
                        calle.set_telefono(updated_t[0])
                        calle.set_rut(updated_r[0])

            # Si el comando calza con el patrón de valid_camino id_calle id_calle
            elif p_valid_camino.match(command):
                ids = re.findall(id_calle, command)
                search_camino = ids[0] + '->' + ids[1]
                existe = False
                existe_id_1 = False
                existe_id_2 = False
                
                # Buscará dentro de todos los caminos
                for camino in array_caminos:
                    # Para cada camino hallado, sacará los id's
                    found_id_1 = camino.split('->')[0]
                    found_id_2 = camino.split('->')[1]
                    
                    # Verificará si los id's buscados existen
                    # independiente de si son el camino buscado
                    if found_id_1 == ids[0]:
                        existe_id_1 = True
                    if found_id_2 == ids[0]:
                        existe_id_2 = True
                    if found_id_1 == ids[1]:
                        existe_id_1 = True
                    if found_id_2 == ids[1]:
                        existe_id_2 = True
                    if camino == search_camino:
                        print('Si se puede')
                        existe = True
                    
                if not existe:
                    # Si los id's no existen, imprimirá lo siguiente
                    if not existe_id_1:
                        print('No existe con'+ ids[0] + 'o' + ids[1])
                    if not existe_id_2:
                        print('No existe con'+ ids[0] + 'o' + ids[1])
                    # Si los id's existen pero no son camino, imprime lo siguiente
                    if existe_id_1 and existe_id_2:
                        print('No se puede')

            else:
                errores.write(command + '\n')
            
