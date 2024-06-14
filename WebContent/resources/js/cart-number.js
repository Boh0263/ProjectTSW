function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function getCartItems() {
    var cartItems = getCookie('cartItems');
    if (cartItems !== null) {
        return Promise.resolve(cartItems);
    } else {
        return new Promise(function(resolve, reject) {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/Carrello', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onload = function() {
                if (this.status >= 200 && this.status < 400) {
                    var resp = JSON.parse(this.response);
                    setCookie('cartItems', resp.totalItems, 1);
                    resolve(resp.totalItems);
                } else {
                    reject(new Error('AJAX request failed'));
                }
            };
            xhr.onerror = function() {
                reject(new Error('AJAX request failed'));
            };
            xhr.send('action=getCartItems');
        });
    }
}

function updateCartNumber() {
	getCartItems().then(function(cartItems) {
		document.getElementById('checkout-items').innerText = cartItems + "b";
	});
	
	window.onload = function() {
    updateCartNumber();
};
}
