<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
         $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
         $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $felhasznalonev, $jelszo)
    {
        $felhasznalonev = $this->prepareData($felhasznalonev);
        $jelszo = $this->prepareData($jelszo);
        $this->sql = "select * from " . $table . " where felhasznalonev = '" . $felhasznalonev . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['felhasznalonev'];
            $dbpassword = $row['jelszo'];
            if ($dbusername == $felhasznalonev && password_verify($jelszo, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $nev, $cim, $email, $telefonszam, $felhasznalonev, $jelszo)
    {
        $nev = $this->prepareData($nev);
        $cim = $this->prepareData($cim);
        $email = $this->prepareData($email);
        $telefonszam = $this->prepareData($telefonszam);
        $felhasznalonev = $this->prepareData($felhasznalonev);
        $jelszo = $this->prepareData($jelszo);
        $jelszo = password_hash($jelszo, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (nev, cim, email, telefonszam, felhasznalonev, jelszo) 
			VALUES ('" . $nev . "','" . $cim . "','" . $email . "','" . $telefonszam . "','" . $felhasznalonev . "','" . $jelszo . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }
    
	function dogsignUp($table, $agresszivE,$eletkor, $fajta, $tulajNev)
    {
        $agresszivE = $this->prepareData($agresszivE);
        $eletkor = $this->prepareData($eletkor);
        $fajta = $this->prepareData($fajta);
        $tulajNev = $this->prepareData($tulajNev);
        $this->sql =
            "INSERT INTO " . $table . " (agresszivE, eletkor, fajta, tulajNev) 
			VALUES ('" . $agresszivE . "','" . $eletkor . "','" . $fajta . "','" . $tulajNev . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }
	
    function appointment($table, $foglalasNapja, $foglalasOraja, $felhasznalonev,$szolgaltatasNev)
    {
        $foglalasNapja = $this->prepareData($foglalasNapja);
        $foglalasOraja = $this->prepareData($foglalasOraja);
        $felhasznalonev = $this->prepareData($felhasznalonev);
		$szolgaltatasNev = $this->prepareData($szolgaltatasNev);
        $this->sql =
            "INSERT INTO " . $table . " (foglalasNapja, foglalasOraja, felhasznalonev, szolgaltatasNev) 
			VALUES ('" . $foglalasNapja . "','" . $foglalasOraja . "','" . $felhasznalonev . "','" . $szolgaltatasNev . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }
    
	

}

?>
