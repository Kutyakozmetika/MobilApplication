<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['felhasznalonev']) && isset($_POST['jelszo'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("felhasznalo", $_POST['felhasznalonev'], $_POST['jelszo'])) {
            echo "Login Success";
        } else echo "Username or Password wrong";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>