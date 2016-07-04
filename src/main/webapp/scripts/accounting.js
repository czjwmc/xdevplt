// Return a formatted number string from a number, example: 999,999.99
function formatMoney(s,n)
{
    n = n >= 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
    r = s.split(".")[1],
    t = "";
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    if (n==0) {
    	return t.split("").reverse().join("");
    } else {
    	return t.split("").reverse().join("") + "." + r;
    }
}

//Return a formatted number string from a number.
function formatNumber(s,n)
{
	n = n >= 0 && n <= 20 ? n : 0;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
    r = s.split(".")[1],
    t = "";
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    if (n==0) {
    	return t.split("").reverse().join("");
    } else {
    	return t.split("").reverse().join("") + "." + r;
    }
}

//Return a formatted discount string from a number.
function formatDiscount(s,n)
{
	n = n >= 0 && n <= 20 ? n : 1;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(),
	r = s.split(".")[1],
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	if (n==0) {
		return t.split("").reverse().join("") + '%';
	} else {
		return t.split("").reverse().join("") + "." + r + '%';
	}
}

//Return a formatted percent string from a number.
function formatPercent(s,n)
{
	n = n >= 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(),
	r = s.split(".")[1],
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	if (n==0) {
		return t.split("").reverse().join("") + '%';
	} else {
		return t.split("").reverse().join("") + "." + r + '%';
	}
}

// Return a number from a formatted string.
function unformat(s) {
	s = s.replace("%","");
    return parseFloat(s.replace(/[^\d\.-]/g, ""));
}

// Add, example: num1.add(num2)
function dcmAdd(arg1,arg2){
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
    return (dcmMul(arg1,m)+dcmMul(arg2,m))/m;
}
Number.prototype.add = function (arg){
    return dcmAdd(this,arg);
}

//Sub, example: num1.sub(num2)
function dcmSub(arg1,arg2){
     return dcmAdd(arg1,-arg2);
}
Number.prototype.sub = function (arg){
    return dcmSub(this,arg);
}

//Mul, example: num1.mul(num2)
function dcmMul(arg1,arg2){
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{m+=s1.split(".")[1].length}catch(e){}
    try{m+=s2.split(".")[1].length}catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}
Number.prototype.mul = function (arg){
    return dcmMul(this,arg);
}

//Div, example: num1.div(num2)
function dcmDiv(arg1,arg2){
    return dcmMul(arg1,1/arg2);
}
Number.prototype.div = function (arg){
    return dcmDiv(this,arg);
}