$('#confirmacaoExclusaoCidadeModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigo = button.data('codigo');
	var descricao = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + descricao + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});

});

$('#confirmacaoExclusaoClienteModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigo = button.data('codigo');
	var descricao = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + descricao + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});

});

$('#confirmacaoExclusaoFreteModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigo = button.data('codigo');
	var descricao = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + descricao + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});

});

$(".phoneNum").mask("(99) 9999-9999");
