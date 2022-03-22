<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['foglalasNapja']) && isset($_POST['foglalasOraja'])) {
    if ($db->dbConnect()) {
        if ($db->appointment("megrendeles", $_POST['foglalasNapja'], $_POST['foglalasOraja'])) {
            echo "Order Success";
        } else echo "Order Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>