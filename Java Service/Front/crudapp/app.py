from flask import Flask,render_template,request,redirect,url_for
import requests
import json

app = Flask(__name__)


@app.route('/')
def hello():
    return render_template('index.html')

############################################# AGENCE ################################################################
    
@app.route('/agence')
def agence():
    res = requests.get("http://localhost:9191/agences")
    res = list(res.json())
    context = res
    return render_template('agence.html',contexts=context)

@app.route('/ajouterAgence', methods=['POST'])
def ajouterAgence():
    code = request.form['code']
    adresse = request.form['adresse']
    nom = request.form['nom']
    telephone = request.form['telephone']

    x = requests.post('http://localhost:9191/addAgence',json = {"code": code, "adresse": adresse, "nom": nom, "telephone":telephone})

    return redirect(url_for('agence'))


@app.route('/deleteAgence/<code>', methods=['GET'])
def deleteAgence(code):

    url = 'http://localhost:9191/deleteAgence/'+code
    x = requests.delete(url)

    print(url)

    return redirect(url_for('agence'))


@app.route('/editAgence', methods=['POST'])
def editAgence():
    code = request.form['code']
    adresse = request.form['adresse']
    nom = request.form['nom']
    telephone = request.form['telephone']

    x = requests.put('http://localhost:9191/updateAgence',json = {"code": code, "adresse": adresse, "nom": nom, "telephone":telephone})
    print(x)

    return redirect(url_for('agence'))


############################################################### CLIENT ####################################################################

@app.route('/client')
def client():
    res = requests.get("http://localhost:9191/clients")
    res = list(res.json())
    context = res
    return render_template('client.html',contexts=context)

@app.route('/ajouterClient', methods=['POST','GET'])
def ajouterClient():
    date_naissance = request.form['date_naissance']
    nom = request.form['nom']
    prenom = request.form['prenom']

    print(date_naissance,nom,prenom)

    x = requests.post('http://localhost:9191/addClient',json = {"date_naissance": date_naissance, "nom": nom, "prenom":prenom})

    print(x)

    return redirect(url_for('client'))


@app.route('/deleteClient/<int:id>', methods=['GET'])
def deleteClient(id):

    url = 'http://localhost:9191/deleteClient/'+str(id)
    requests.delete(url)

    print(url)

    return redirect(url_for('client'))


@app.route('/editClient', methods=['POST'])
def editClient():
    idt = request.form['id']
    date_naissance = request.form['date_naissance']
    nom = request.form['nom']
    prenom = request.form['prenom']
    idt= int(idt)

    x = requests.put('http://localhost:9191/updateClient',json = {"id": idt, "date_naissance": date_naissance, "nom": nom, "prenom":prenom})
    print(x)
    print(type(idt))

    return redirect(url_for('client'))
####################################################################### COMPTE ##############################################################

@app.route('/compte')
def compte():
    res = requests.get("http://localhost:9191/comptes")
    res = list(res.json())
    context = res
    return render_template('compte.html',contexts=context)

@app.route('/ajouterCompte', methods=['POST','GET'])
def ajouterCompte():
    decouvert = request.form['decouvert']
    solde = request.form['solde']

    x = requests.post('http://localhost:9191/addCompte',json = {"decouvert": decouvert, "solde":solde})

    print(x)

    return redirect(url_for('compte'))


@app.route('/deleteCompte/<int:id>', methods=['GET'])
def deleteCompte(id):

    url = 'http://localhost:9191/deleteCompte/'+str(id)
    requests.delete(url)
    
    print(url)

    return redirect(url_for('compte'))


@app.route('/editCompte', methods=['POST'])
def editCompte():
    idt = request.form['id']
    decouvert = request.form['decouvert']
    solde = request.form['solde']
    idt= int(idt)

    x = requests.put('http://localhost:9191/updateCompte',json = {"id": idt, "decouvert": decouvert, "solde":solde})
    print(x)
    print(type(idt))

    return redirect(url_for('compte'))

if __name__ == '__main__':
    app.run()