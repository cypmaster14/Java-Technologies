function handleAddSkillRequest(xhr, status, args) {
    if (!args.success) {
        PF('skillAdd').jq.effect("shake", {times: 5}, 100);
    }
    else {
        PF('skillAdd').hide();
        $('#loginLink').fadeOut();
    }
}