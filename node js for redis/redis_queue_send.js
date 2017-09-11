var fs = require("fs"),
    redis = require("redis"),
    rhost = "localhost",
    rport = 6379,
    qname = "queue:subscribedEvents:1",
    client = redis.createClient("redis://" + rhost + ":" + rport),
    fileName = process.argv[2],
    msgCount = process.argv[3];

var num = 0;
var instantiateTemplate = function(templateStr) {
	var result = templateStr;
	result = result.replace("${subscriptionId}", 41);
	result = result.replace("${mailingId}", 13129);
	result = result.replace("${reportId}", 12306);
	result = result.replace("${recipientId}", 20022);
	// result = result.replace("${recipientId}", 501);
	result = result.replace("${listId}", 11086);
	// result = result.replace("${listId}", 999999);
	result = result.replace("${mailingTemplateId}", 13128);
	
	var msec = num%1000;
	var sec=(Math.floor(num/1000))%60;

	result = result.replace("${sec.msec}", sec + "." + msec);

	num = num + 1;
	return result;
}

fs.readFile(process.argv[2], 'utf8', function (err,data) {
  if (err) {
    return console.log(err);
  }
  for (var cnt=0; cnt<msgCount; cnt++) {
    client.lpush(qname, instantiateTemplate(data), redis.print);
  } 
  console.log("DONE");
  client.quit();
});


client.on("error", function(err) {
	console.log("ERROR: " + err);
});


