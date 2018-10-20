<html>

<head>
<title>Demo</title>
</head>

<body>
	<h2 align="center">Carga de archivos</h2>
	<hr>
	<br></br>
	<form action="http://localhost:8080/accidents/predict"
		enctype="multipart/form-data" method="POST">
		<input type="file" name="file" /> <input type="submit" value="Predecir" />
	</form>
	<br></br>
	<hr>
	<h6 align="right">
		<i>PAP_version 0.0.1</i>
	</h6>

<!-- 	<form action="http://localhost:8080/siniestros/predict" method="POST"> -->
<!-- 		<br> <input type="submit" value="Predecir"> -->
<!-- 	</form> -->
</body>
</html>
