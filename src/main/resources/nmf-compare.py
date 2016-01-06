#!/usr/bin/python

import numpy as np
from sklearn.decomposition import ProjectedGradientNMF
import json
import nimfa

from os.path import dirname, abspath
from os.path import join
from warnings import warn

from sklearn.metrics.cluster import normalized_mutual_info_score
    
################################################################
# Implementation Map

implementation_map = {}

def implementation(name):
    def wrapper(func):
        implementation_map[name] = func
        return func

    return wrapper


################################################################
# Matrix

class Matrix:
    def __init__(self, data):
        #print '\nInstantiating'
        self.data = np.array(data)

    @classmethod
    def from_file(cls, filename):
        with open(filename, "r") as f:
            json_data = json.load(f)

        if "denseMatrix" in json_data:
            num_rows = json_data["denseMatrix"]["numRows"]
            num_cols = json_data["denseMatrix"]["numCols"]
            matrix = json_data["denseMatrix"]["data"]

            return cls(np.array(matrix).reshape((num_rows, num_cols)))

        if "sparseMatrix" in json_data:
            num_rows = json_data["sparseMatrix"]["numRows"]
            num_cols = json_data["sparseMatrix"]["numCols"]
            matrix = np.zeros((num_rows, num_cols))

            for entry in json_data["sparseMatrix"]["entries"]:
                i = entry["rowIndex"]
                j = entry["colIndex"]
                value = entry["value"]

                matrix[i][j] = value

            return cls(matrix)

        return cls([[0]])

    def to_file(self, filename):
        try:
            d = {
                "denseMatrix": {
                    "numRows": self.data.shape[0],
                    "numCols": self.data.shape[1],
                    "data": self.data.ravel().tolist()
                }
            }

            with open(filename, "w") as f:
                json.dump(d, f)
        except:
            print filename, self.data.shape





#def main(implementation, matrix_filename, w_filename, h_filename, rank,func='standard',matrix1=None,matrix2=None, type="nimfa_nmf"):
#def main(implementation, matrix1_filename, matrix2_filename, rank, matrix_type, arr):
def main(implementation, rank, matrix_type, alg_typesStr):
     
    #print '\n\ncompare\n\n'
    #print 'matrix1_filename: ' + str(matrix1_filename)
    #print 'matrix2_filename: ' + str(matrix2_filename)
    #print 'rank: ' + str(rank)
    #print 'matrix_type: ' + matrix_type
    #print 'arr: ' + str(arr.split(","))
    
    file_str = ''
    file_str += "date,bucket,count\n"
    
    factorized_matricies_dir = 'src/main/resources/static/data/'
    
    alg_types = alg_typesStr.split(",")#['nimfa_nmf','nimfa_icm','nimfa_lsnmf']
    
    #print 'comparing...'
    for i in range(0,len(alg_types)):
        #print 'i: ' + str(i) + " " + alg_types[i]#alg_types[i]
        for j in range(0,len(alg_types)):
            #print " to " + str(j) + " " + alg_types[j]
            matrix_filename_1 = "MAT-nmf-python" + "-" + matrix_type + "-" + alg_types[i] + "-" + rank + ".json"
            matrix_1 = Matrix.from_file(matrix_filename_1)
    
            matrix_filename_2 = "MAT-nmf-python" + "-" + matrix_type + "-" + alg_types[j] + "-" + rank + ".json"
            matrix_2 = Matrix.from_file(matrix_filename_2)
    
            xStr = ''
            xStr = "2012-07-20"
    
    
            if i == 0:
                xStr = "2012-07-20"
            elif i == 1:
                xStr = "2012-07-21"
            elif i == 2:
                xStr = "2012-07-22"
            elif i == 3:
                xStr = "2012-07-23"
            else:
                xStr = "2012-07-24"
    
            file_str += str(xStr) + "," + str(j+1) + "," + str(normalized_mutual_info_score(matrix_1.data.flatten(),matrix_2.data.flatten())) + "\n"

    output_file = 'output_test'          
    #text_file = open("src/main/resources/static/data/output2.csv", "w")
    text_file = open("src/main/resources/static/data/" + output_file + "-" + matrix_type + "-" + rank + ".csv", "w")
    text_file.write(file_str)
    text_file.close()        
    
    '''
    SKLEARN("sklearn_nmf"),
        NIMFA_NMF("nimfa_nmf"),
        NIMFA_LSNMF("nimfa_lsnmf"),
        /*
        NIMFA_BD("nimfa_bd");
        */
        NIMFA_ICM("nimfa_icm"),
        NIMFA_LFNMF("nimfa_lfnmf"),
        /*
        NIMFA_NSNMF("nimfa_nsnmf"),
        NIMFA_PMF("nimfa_pmf"),
        NIMFA_PSMF("nimfa_psmf"),
        */
        NIMFA_SNMF("nimfa_snmf"),
        /*
        NIMFA_SNMNMF("nimfa_snmnmf"),
        */
        NIMFA_PMFCC("nimfa_pmfcc");
    '''
    
        
if __name__ == "__main__":
    import argparse

    print 'args'
    parser = argparse.ArgumentParser()
    parser.add_argument("--implementation")
    #parser.add_argument("--matrix1-filename")
    #parser.add_argument("--matrix2-filename")
    parser.add_argument("--rank")
    parser.add_argument("--matrix-type")
    parser.add_argument("--alg_typesStr")
    
    args = parser.parse_args()

    print 'args'
    main(**vars(args))



















    '''
    print 'comparing...'
    for i in range(0,len(alg_types)):
        print 'i: ' + str(i) + " " + alg_types[i]#alg_types[i]
        for j in range(0,len(alg_types)):
            print " to " + str(j) + " " + alg_types[j]
            
            matrix_filename_1 = "MAT-nmf-python" + "-" + matrix_type + "-" + alg_types[i] + "-" + rank + ".json"
            matrix_1 = Matrix.from_file(matrix_filename_1)
    
            matrix_filename_2 = "MAT-nmf-python" + "-" + matrix_type + "-" + alg_types[j] + "-" + rank + ".json"
            matrix_2 = Matrix.from_file(matrix_filename_2)
    
            
            #print 'matrix1_filename: ' + matrix1_filename + ' matrix2_filename: ' + matrix2_filename
            
            xStr = ''
            xStr = "2012-07-20"
    
    
            if i == 0:
                xStr = "2012-07-20"
            elif i == 1:
                xStr = "2012-07-21"
            elif i == 2:
                xStr = "2012-07-22"
            elif i == 3:
                xStr = "2012-07-23"
            else:
                xStr = "2012-07-24"
    
            file_str += str(xStr) + "," + str(j+1) + "," + str(normalized_mutual_info_score(matrix_1.data.flatten(),matrix_2.data.flatten())) + "\n"
    '''
    
    '''
    matrix_filename_1 = matrix1_filename + '-' + str(rank) + '.json'#'MAT-nmf-python-h-nimfa_nmf-' + str(i) + '.json'
    matrix_1 = Matrix.from_file(matrix_filename_1)
    
    print 'matrix_filename_1: ' + matrix_filename_1        
    
    matrix_filename_2 = matrix2_filename + '-' + str(rank) + '.json'#'MAT-nmf-python-h-nimfa_icm-' + str(j) + '.json'
    matrix_2 = Matrix.from_file(matrix_filename_2)
           
    print 'matrix_filename_2: ' + matrix_filename_2 
    
    xStr = ''
    xStr = "2012-07-20"
    
    if rank == 1:
        xStr = "2012-07-20"
    elif rank == 2:
        xStr = "2012-07-21"
    elif rank == 3:
        xStr = "2012-07-22"
    elif rank == 4:
        xStr = "2012-07-23"
    else:
        xStr = "2012-07-24"
    
    file_str += str(xStr) + "," + str(2) + "," + str(normalized_mutual_info_score(matrix_1.data.flatten(),matrix_2.data.flatten())) + "\n"
                
    
    output_file = 'output_test'          
    #text_file = open("src/main/resources/static/data/output2.csv", "w")
    text_file = open("src/main/resources/static/data/" + output_file +".csv", "w")
    text_file.write(file_str)
    text_file.close()
    '''
    
    '''
    #matrix1_filename = ''
    #matrix2_filename = ''
    
    file_str = ''
    
    file_str += "date,bucket,count\n"
    
    for i in range(1,6):
        
        matrix_filename_nmf = matrix1_filename + '-' + str(i) + '.json'#'MAT-nmf-python-h-nimfa_nmf-' + str(i) + '.json'
        matrix_nmf = Matrix.from_file(matrix_filename_nmf)
        
        for j in range(1,6):
            matrix_filename_icm = matrix2_filename + '-' + str(j) + '.json'#'MAT-nmf-python-h-nimfa_icm-' + str(j) + '.json'
            matrix_icm = Matrix.from_file(matrix_filename_icm)
            #print 'length i: ' + str(i) + " " + str(len(matrix_nmf.data.flatten()))
            #print 'length j: ' + str(j) + " " + str(len(matrix_icm.data.flatten()))
            
            xStr = i
            if i == 1:
                xStr = "2012-07-20"
            elif i == 2:
                xStr = "2012-07-21"
            elif i == 3:
                xStr = "2012-07-22"
            elif i == 4:
                xStr = "2012-07-23"
            else:
                xStr = "2012-07-24"
            
        
            
            if i == j:
                #print str(xStr) + "," + str(j) + "," + str(normalized_mutual_info_score(matrix_nmf.data.flatten(),matrix_icm.data.flatten())) + "\n"
                file_str += str(xStr) + "," + str(j) + "," + str(normalized_mutual_info_score(matrix_nmf.data.flatten(),matrix_icm.data.flatten())) + "\n"
                
            else:
                #print str(xStr) + "," + str(j) + "," + str(0) + "\n"  
                #file_str += str(xStr) + "," + str(j) + "," + str(normalized_mutual_info_score(matrix_nmf.data.flatten(),matrix_icm.data.flatten())) + "\n"
                file_str += str(xStr) + "," + str(j) + "," + str(0) + "\n"
    
    output_file = 'MAT' + '-' + 'compare' + '-' + matrix_type + '-' + 'nmf' + '-' + 'icm' + '-' + '5'
              
    #text_file = open("src/main/resources/static/data/output2.csv", "w")
    text_file = open("src/main/resources/static/data/" + output_file +".csv", "w")
    text_file.write(file_str)
    text_file.close()
    '''
    
    
    

'''
################################################################
# NMF

@implementation("sklearn_nmf")
def sklearn_nmf(matrix, rank):
    nmf_model = ProjectedGradientNMF(n_components=rank,
                                     init='random',
                                     random_state=0)

    W = nmf_model.fit_transform(matrix)
    H = nmf_model.components_

    return (W, H)

@implementation("nimfa_nmf")
def nimfa_nmf(matrix, rank, model_factory=nimfa.Nmf):
    
    #print 'in nimfa_nmf with matrix: \n' + str(matrix) + '\n\n and rank: ' + str(rank)
    med = nimfa.examples.medulloblastoma
    face = nimfa.examples.cbcl_images
    
    
    
    #print 'matrix>>>: ' + str(matrix)
    
    model = model_factory(matrix, rank=rank)
    model_fit = model()


    W = model_fit.basis()
    H = model_fit.coef()
    
    
    #print 'W: ' + str(W)
    W1_flat = list(W.flat)
    #print 'flat W: ' + str(W1_flat)
    
    model_factory=nimfa_icm
    model_fit = model()
    
    W = model_fit.basis()
    H = model_fit.coef()
    
    
    #print 'W: ' + str(W)
    W2_flat = list(W.flat)
    #print 'flat W: ' + str(W2_flat)
    
    from sklearn.metrics.cluster import normalized_mutual_info_score
    
    #print str(normalized_mutual_info_score(W1_flat,W2_flat))

    #print 'H: ' + str(H)
    
    return (W, H)

@implementation("nimfa_lsnmf")
def nimfa_lsnmf(matrix, rank):
    return nimfa_nmf(matrix, rank, nimfa.Lsnmf)

@implementation("nimfa_bd")
def nimfa_bd(matrix, rank):
    return nimfa_nmf(matrix, rank, nimfa.Bd)

@implementation("nimfa_bmf")
def nimfa_bmf(matrix, rank):
    return nimfa_nmf(matrix, rank, nimfa.Bmf)

@implementation("nimfa_icm")
def nimfa_icm(matrix, rank):
    return nimfa_nmf(matrix, rank, nimfa.Icm)

@implementation("nimfa_lfnmf")
def nimfa_lfnmf(matrix, rank):
    print 'doing lfnmf'
    return nimfa_nmf(matrix, rank, nimfa.Lfnmf)

@implementation("nimfa_nsnmf")
def nimfa_nsnmf(matrix, rank):
    return nimfa_nmf(matrix, rank, nimfa.Nsnmf)

@implementation("nimfa_pmf")
def nimfa_pmf(matrix, rank):
    return nimfa_nmf(matrix, rank, nimfa.Pmf)

@implementation("nimfa_psmf")
def nimfa_psmf(matrix, rank):
    return nimfa_nmf(matrix, rank, nimfa.Psmf)

@implementation("nimfa_snmf")
def nimfa_snmf(matrix, rank):
    return nimfa_nmf(matrix, rank, nimfa.Snmf)

@implementation("nimfa_snmnmf")
def nimfa_snmnmf(matrix, rank):
    return nimfa_nmf(matrix, rank, nimfa.Snmnmf)

@implementation("nimfa_pmfcc")
def nimfa_pmfcc(matrix, rank):
    return nimfa_nmf(matrix, rank, nimfa.Pmfcc)





try:
    #from PIL.Image import open, fromarray, new
    #from PIL.ImageOps import expand
    from PIL import Image
    from PIL import ImageOps
except ImportError as exc:
    warn("PIL must be installed to run CBCL images example.")
'''