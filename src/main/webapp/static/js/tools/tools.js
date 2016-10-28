function sortArray(data) {
    if (data) {
        for (var i = 0; i < data.length; i++) {
            data[i].priority = parseInt(data[i].priority);
        }
    }
};

function cleanArray(array){
    if(array && array.length != 0){
        array.pop();
    }
}

/*
 * This method will check the incoming url and do the following modification[if the url without "https://" or "http://" then added "https://" to
 * the front of url to the incoming url:
 * google.com -> https://google.com
 * www.google.com -> https://www.google.com
 * */
//
//
// https://google.com -> https://google.com
// http://google.com -> http://google.com
function dbmURLValidator(url){
    if (!url || (url.length > 7 && url.substring(0, 8) == "https://") || (url.length > 6 && url.substring(0, 7) == "http://")) {
        return url;
    }
    return "https://" + url;
}