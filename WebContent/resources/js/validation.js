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
	if(username.length !== 0 && !pattern.test(username)) {
		return true;
	} else {
		return false;
	}
}
function validateEmail(email) {
	const pattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
	if (email.length !== 0 && !pattern.test(email)) {
	 	return true;
	} else { 
		return false;
	}
}

function validatePassword(password)
{
	const pattern = /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z])(?=.*[a-z])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
	if(!pattern.test(password))
	{
		return true;
	} else {
		return false;
	}
}

function validateNomeProdotto(name)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(name))
	{
		return true;
	} else {
        return false;
    }
}

function validateGiacenza(giacenza)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(giacenza))
	{
		return true;
	} else {
		return false;
	}
}

function validateDescrizione(desc)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(desc))
	{
		return true;
	} else {
		return false;
	}
}

function validatePrezzo(prezzo)
{
	const pattern = /^[0-9]{1,3}(\.[0-9]{3})*(,\d{1,2})?€$|^,(\d{1,2})€$/;
	if(!pattern.test(prezzo))
	{
		return true;
	} else { 
		return false;
	}
}

function validateIVA(iva)
{
	const pattern = /^\d{2}$/;	//esattamente due cifre
	if(!pattern.test(iva))
	{
		return true;
	} else {
		return false;
	}
}

function validateTipo(tipo)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(tipo))
	{
		return true;
	}
	return false;
}

function validateGenere(genere)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(genere))
	{
		return true;
	} else {
		return false;
	}
}

function validateMateriale(mat)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(mat))
	{
		return true;
	} else {
		return false;
	}
}

function validatePezzo(pezzo)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(pezzo))
	{
		return true;
	} else {
		return false;
	}
}

function validateUtilizzo(usage)
{
	const pattern = /^[a-zA-Z]+$/;
	if(!pattern.test(usage))
	{
		return true;
	} else {
		return false;
	}
}