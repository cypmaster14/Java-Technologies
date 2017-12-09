window.addEventListener("beforeunload", function (event) {
    console.log("Unload");
});


function handleAddStudentRequest(xhr, status, args) {
    console.log(args);
    if (args.success) {
        PF('studentAddWidget').hide();
        $('#loginLink').fadeOut();
    }
}

function sleep(miliseconds) {
    var currentTime = new Date().getTime();

    while (currentTime + miliseconds >= new Date().getTime()) {
    }
}