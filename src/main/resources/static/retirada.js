document.addEventListener('DOMContentLoaded', function() {
    document.querySelector('.Logo').addEventListener('click', function() {
        document.querySelector('.sidebar').classList.toggle('open');
    });

    window.addEventListener('click', function(event) {
        var sidebar = document.querySelector('.sidebar');
        if (!event.target.matches('.Logo') && sidebar.classList.contains('open')) {
            sidebar.classList.remove('open');
        }
    });

    document.querySelector('.remove-product-btn').addEventListener('click', function(event) {
        event.preventDefault();

        var productId = document.getElementById('productId').value;

        fetch('/api/produtos/' + productId, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('Produto removido com sucesso!');
                    document.getElementById('productId').value = '';
                } else {
                    console.error('Erro ao remover produto:', response.statusText);
                    alert('Erro ao remover produto. Por favor, tente novamente.');
                }
            })
            .catch(error => {
                console.error('Erro ao remover produto:', error);
                alert('Erro ao remover produto. Por favor, tente novamente mais tarde.');
            });
    });

    document.querySelector('.update-quantity-btn').addEventListener('click', function(event) {
        event.preventDefault();

        var productId = document.getElementById('productIdToUpdate').value;
        var newQuantity = document.getElementById('quantity').value;

        fetch('/api/produtos/' + productId + '/atualizar-quantidade?quantidade=' + newQuantity, {
            method: 'PUT'
        })
            .then(response => {
                if (response.ok) {
                    alert('Quantidade do produto atualizada com sucesso!');
                    document.getElementById('productIdToUpdate').value = '';
                    document.getElementById('quantity').value = '';
                } else {
                    console.error('Erro ao atualizar a quantidade do produto:', response.statusText);
                    alert('Erro ao atualizar a quantidade do produto. Por favor, tente novamente.');
                }
            })
            .catch(error => {
                console.error('Erro ao atualizar a quantidade do produto:', error);
                alert('Erro ao atualizar a quantidade do produto. Por favor, tente novamente mais tarde.');
            });
    });
});
