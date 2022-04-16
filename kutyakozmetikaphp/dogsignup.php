<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['agresszivE'])&& isset($_POST['eletkor']) &&  isset($_POST['fajta']) && isset($_POST['tulajNev'])) {
    if ($db->dbConnect()) {
        if ($db->dogsignUp("kutya", $_POST['agresszivE'],  $_POST['eletkor'], $_POST['fajta'], $_POST['tulajNev'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>