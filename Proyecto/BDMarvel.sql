


CREATE DATABASE MarvelPro;


 

USE MarvelPro;

 


 

CREATE TABLE Personaje
(
    IdPersonaje  INT AUTO_INCREMENT ,
    nombre VARCHAR (100) NOT NULL,
    edad INT  NOT NULL, 
    descripcion VARCHAR (100) NOT NULL, 
	estatus BOOLEAN NOT NULL DEFAULT true,
    
    CONSTRAINT PK_IdPersonaje PRIMARY KEY(IdPersonaje )
)ENGINE = MYISAM;

CREATE TABLE Usuario
(
    IdUsuario  INT AUTO_INCREMENT ,
    userName VARCHAR (100) NOT NULL,
    passw VARCHAR (100)  NOT NULL, 
	estatus BOOLEAN NOT NULL DEFAULT true,
    
    CONSTRAINT PK_IdUsuario PRIMARY KEY(IdUsuario )
)ENGINE = MYISAM;


CREATE INDEX IX_Personaje ON Personaje (IdPersonaje);
CREATE INDEX IX_Usuario ON Usuario (IdUsuario);




	

	INSERT INTO Personaje(nombre, edad,descripcion,estatus )
      VALUES('thor',1500,'hijo de odin', true);
      
    INSERT INTO Usuario(userName, passw,estatus )
      VALUES('juan','lolero',true);  
      
      

SELECT * FROM Personaje;
SELECT * FROM Usuario;