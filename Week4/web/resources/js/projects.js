function handleAddProjectRequest(xhr, status, args) {
    console.log(args);
    if (args.success) {
        PF('addProjectWidget').hide();
        $('#loginLink').fadeOut();
    } else {
        alert("Failed to add the new student");
    }
}