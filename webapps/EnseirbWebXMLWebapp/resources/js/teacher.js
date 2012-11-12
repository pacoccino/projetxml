function postTeacher(string) {
	jQuery.post('teacher/post', string, function(data) {
											alert("Retour serveur: " + data);
										}, "text");
}
