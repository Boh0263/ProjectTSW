/*
Il campo password deve necessariamente avere:
 - almeno un carattere alfabetico minuscolo
 - almeno un carattere alfabetico maiuscolo
 - almeno un carattere numerico
 - almeno un carattere speciale, come % ^ & * ! @ # $
 - una lunghezza compresa tra 8 e 16
*/

//Togliere l'alert e sostituirlo con un indicazione attorno al campo assieme al messaggio sotto
//Una cosa del genere anche per la password
//volendo la stessa cosa anche sul modulo per aggiungere i prodotti (ed eventuali altri?)

// let float = document.getElementById("prezzo");
// let floatVal = float.value
// const regFloat = /^[+-]?\d+(\.\d+)?$/
// if(!regFloat.test(floatVal))

function validateUsername(username)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(username)) {
		return false;
	} else {
		return true;
	}
}
function validateEmail(email) {
	const pattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
	if (!pattern.test(email)) {
	 	return false;
	} 
	else return true;
}

function validatePassword(password)
{
	const pattern = /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z])(?=.*[a-z])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
	if(!pattern.test(password))
	{
		return false;
	} else {
		return true;
	}
}

function validateNomeProdotto(name)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(name))
	{
		return false;
	} else {
        return true;
    }
}

function validateGiacenza(giacenza)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(giacenza))
	{
		return false;
	} else {
		return true;
	}
}

function validateDescrizione(desc)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(desc))
	{
		return false;
	} else {
		return true;
	}
}

function validatePrezzo(prezzo)
{
	const pattern = /^[0-9]{1,3}(\.[0-9]{3})*(,\d{1,2})?€$|^,(\d{1,2})€$/;
	if(!pattern.test(prezzo))
	{
		return false;
	} else { 
		return true;
	}
}

function validateIVA(iva)
{
	const pattern = /^\d{2}$/;	//esattamente due cifre
	if(!pattern.test(iva))
	{
		return false;
	} else {
		return true;
	}
}

function validateTipo(tipo)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(tipo))
	{
		return false;
	}
}

function validateGenere(genere)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(genere))
	{
		return false;
	} else {
		return true;
	}
}

function validateMateriale(mat)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(mat))
	{
		return false;
	} else {
		return true;
	}
}

function validatePezzo(pezzo)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(pezzo))
	{
		return false;
	} else {
		return true;
	}
}

function validateUtilizzo(usage)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(usage))
	{
		return false;
	} else {
		return true;
	}
}