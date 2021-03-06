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
    context = list(res.json())
    # context[0]['comptes'].append({"name": "Visa"})
    # context[1]['comptes'].append({"name": "Master Card"})

    r = requests.get("http://localhost:9191/comptes")
    comptes = list(r.json())
    # print(comptes)

    # print(context)
    
    return render_template('agence.html',contexts=context,comptes=comptes)

@app.route('/ajouterAgence', methods=['POST'])
def ajouterAgence():
    code = request.form['code']
    adresse = request.form['adresse']
    nom = request.form['nom']
    telephone = request.form['telephone']
    comptesId = request.form.getlist('comptes')
    comptes = []

    for idCompte in comptesId:
        r = requests.get("http://localhost:9191/comptes/"+idCompte)
        compte = r.json()
        comptes.append(compte)

    x = requests.post('http://localhost:9191/addAgence',json = {"code": code, "adresse": adresse, "nom": nom, "telephone":telephone, "comptes": comptes})

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
    comptesId = request.form.getlist('comptes')
    comptes = []

    for idCompte in comptesId:
        r = requests.get("http://localhost:9191/comptes/"+idCompte)
        compte = r.json()
        comptes.append(compte)

    x = requests.put('http://localhost:9191/addAgence',json = {"code": code, "adresse": adresse, "nom": nom, "telephone":telephone, "comptes": comptes})


    print(x)

    return redirect(url_for('agence'))

@app.route('/agence/<code>', methods=['GET'])
def agenceCompte(code):
    url = 'http://localhost:9191/agences/'+code
    res = requests.get(url)
    agence = res.json()
    print(agence)
    nom = "de l'Agence "+agence['nom']

    comptes = list(agence['comptes'])

    print(nom)
    print(comptes)


    return render_template('agenceCompte.html',comptes=comptes, nom = nom)


############################################################### CLIENT ####################################################################

@app.route('/client')
def client():
    res = requests.get("http://localhost:9191/clients")
    res = list(res.json())
    context = res
    r = requests.get("http://localhost:9191/comptes")
    comptes = list(r.json())
    return render_template('client.html',contexts=context,comptes=comptes)

@app.route('/ajouterClient', methods=['POST','GET'])
def ajouterClient():
    date_naissance = request.form['date_naissance']
    nom = request.form['nom']
    prenom = request.form['prenom']

    comptesId = request.form.getlist('comptes')
    comptes = []

    for idCompte in comptesId:
        r = requests.get("http://localhost:9191/comptes/"+idCompte)
        compte = r.json()
        comptes.append(compte)

    print(comptes)

    x = requests.post('http://localhost:9191/addClient',json = {"prenom":prenom,"nom": nom,"date_naissance": date_naissance, "comptes": comptes} )




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


@app.route('/client/<int:id>', methods=['GET'])
def clientCompte(id):
    url = 'http://localhost:9191/clients/'+str(id)
    res = requests.get(url)
    client = res.json()
    nom = "du Client "+client['prenom']+" "+client['nom']

    comptes = list(client['comptes'])

    print(nom)
    print(comptes)


    return render_template('agenceCompte.html',comptes=comptes, nom = nom)
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