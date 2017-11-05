
function handleAddSkillToStudentRequest(xhr, status, args) {
    if (args.success) {
        PF('addSkillToStudent').hide();
        $('#loginLink').fadeOut();
    } else {
        alert("Failed to add the skill to student");
    }

}