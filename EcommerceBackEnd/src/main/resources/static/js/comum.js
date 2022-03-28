$(document).ready(function() {
	$("#logoutLink").on("click", function(e) {
		e.preventDefault();
		document.logoutForm.submit();

	});
	
	custumizarMenu();
});

$(document).ready(
	function() {
		$(".link-delete")
			.on(
				"click",
				function(e) {
					e.preventDefault();
					link = $(this);
					userId = link.attr("userId");
					$("#yesButton").attr("href",
						link.attr("href"));
					$("#modalBody").text(
						"Confirmar exclusão do usuário "
						+ userId + "?");
					$("#confirmModal").modal("show");

				});
	});

function custumizarMenu(){
	$(".navbar .dropdown").hover(
		function(){
			$(this).find('.dropdown-menu').first().stop(true,true).delay(250).slideDown();
		},
		function(){
			$(this).find('.dropdown-menu').first().stop(true,true).delay(10).slideUp();
		}
	);
/*	
	$(".dropdown > a").click(function (){
		location.href = this.href;
	})
	
*/
}
