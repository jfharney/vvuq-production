#!/usr/bin/python

import numpy as np
from sklearn.decomposition import ProjectedGradientNMF
import json
import nimfa

from os.path import dirname, abspath
from os.path import join
from warnings import warn

################################################################
# Implementation Map

implementation_map = {}

def implementation(name):
    def wrapper(func):
        implementation_map[name] = func
        return func

    return wrapper


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
    
    
    #print str('dir medulloblastoma: ' + dir(med))
    
    model = model_factory(matrix, rank=rank)
    model_fit = model()


    W = model_fit.basis()
    H = model_fit.coef()
    
    
    print 'W: ' + str(W)
    W1_flat = list(W.flat)
    print 'flat W: ' + str(W1_flat)
    
    model_factory=nimfa_icm
    model_fit = model()
    
    W = model_fit.basis()
    H = model_fit.coef()
    
    
    print 'W: ' + str(W)
    W2_flat = list(W.flat)
    print 'flat W: ' + str(W2_flat)
    
    from sklearn.metrics.cluster import normalized_mutual_info_score
    
    print str(normalized_mutual_info_score(W1_flat,W2_flat))

    #print 'H: ' + str(H)
    '''
    P1 = W*H  
    
    print 'P1: ' + str(P1)
    
    
    model_factory=nimfa.Nmf
    model = model_factory(matrix, rank=rank)
    model_fit = model()

    W = model_fit.basis()
    H = model_fit.coef()

    print 'W: ' + str(W)
    print 'H: ' + str(H)
    
    P2 = W*H
    
    print 'P2: ' + str(P2)
    
    from sklearn.metrics.cluster import normalized_mutual_info_score
    
    print str(normalized_mutual_info_score(P1,P2))
    '''
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

################################################################
# Matrix

class Matrix:
    def __init__(self, data):
        print '\nInstantiating'
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


################################################################
# CBCL Faces example
def read():
    
    #"""
    #Read face image data from the CBCL database. The matrix's shape is 361 (pixels) x 2429 (faces).

    #Step through each subject and each image. Images' sizes are not reduced.

    #Return the CBCL faces data matrix.
    #"""
    
    print("\nReading CBCL faces database\n\n")
    #/Users/8xo/software/nimfa/nimfa/nimfa/examples
    #dir = join(dirname('/Users/8xo/software/nimfa/nimfa/nimfa/examples'), 'datasets', 'CBCL_faces', 'face')
    dir = join(dirname(dirname(abspath(__file__))), 'datasets', 'CBCL_faces', 'face')
    V = np.zeros((19 * 19, 2429))
    for image in range(2429):
        #print 'image'
        #print 'dir: ' + str(join(dir, "face0%s.pgm" % str(image + 1).zfill(4)))
        im = Image.open(join(dir, "face0%s.pgm" % str(image + 1).zfill(4)))
        #print 'after dir: ' + str(join(dir, "face0%s.pgm" % str(image + 1).zfill(4)))
        
        V[:, image] = np.asarray(im).flatten()
    return V

def preprocess(V):
    """
    Preprocess CBCL faces data matrix as Lee and Seung.

    Return normalized and preprocessed data matrix.

    :param V: The CBCL faces data matrix.
    :type V: `numpy.matrix`
    """
    #print("Data preprocessing")
    V = (V - V.mean()) / np.sqrt(np.multiply(V, V).mean())
    V = np.maximum(np.minimum((V + 0.25) * 0.25, 1), 0)
    return V

################################################################
# Main

def find_score(matrix, rank, type1, type2):
    print 'find score for type1: ' + str(type1) + ' type2: ' + str(type2) + ' rank: ' + str(rank)


def factorize(V):
    """
    Perform LSNMF factorization on the CBCL faces data matrix.

    Return basis and mixture matrices of the fitted factorization model.

    :param V: The CBCL faces data matrix.
    :type V: `numpy.matrix`
    """
    lsnmf = nimfa.Lsnmf(V, seed="random_vcol", rank=49, max_iter=50, sub_iter=10,
                        inner_sub_iter=10, beta=0.1, min_residuals=1e-8)
    print("Algorithm: %s\nInitialization: %s\nRank: %d" % (lsnmf, lsnmf.seed, lsnmf.rank))
    fit = lsnmf()
    sparse_w, sparse_h = fit.fit.sparseness()
    print("""Stats:
            - iterations: %d
            - final projected gradients norm: %5.3f
            - Euclidean distance: %5.3f
            - Sparseness basis: %5.3f, mixture: %5.3f""" % (fit.fit.n_iter,
                                                            fit.distance(),
                                                            fit.distance(metric='euclidean'),
                                                            sparse_w, sparse_h))
    return fit.basis(), fit.coef()





#def main(implementation, matrix_filename, w_filename, h_filename, rank,func='standard',matrix1=None,matrix2=None, type="nimfa_nmf"):
def main(implementation, matrix_filename, w_filename, h_filename, rank,func='standard',matrix1=None,matrix2=None, type="nimfa_icm", model_factory=nimfa.Nmf):
    
    print 'type: ' + type
    
    #print 'reading V...'
    V = read()
    #print 'preprocessing V...'
    V = preprocess(V)
    
    #print 'rank: ' + str(rank)
    
    matrix = Matrix.from_file(matrix_filename)
    
    print 'matrix: ' + str(matrix)
    model_fit = None
    
    if type == 'nimfa_nmf':
        model_factory = nimfa.Nmf
        model = model_factory(V, rank=rank)
        #model = model_factory(matrix, rank=rank)
        model_fit = model()
    elif type == 'nimfa_icm':
        model_factory = nimfa.Icm
        model = model_factory(V, rank=rank)
        #model = model_factory(matrix, rank=rank)
        model_fit = model()
    
    
    W = model_fit.basis()
    H = model_fit.coef()
    
    print 'h_filename: ' + str(w_filename)
    
    print 'Matrix class: ' + str(dir(H))
    
    print 'H: ' + str(H)
    
    print 'np.array(W): ' + str(np.array(H))
    
    W = Matrix(W)
    H = Matrix(H)
    
    W.to_file(w_filename)
    H.to_file(h_filename)
    #np.savetxt(w_filename, W)
    
    '''
    W.to_file(w_filename)
    H.to_file(h_filename)
    #print 'W: ' + str(W)
    #print 'length: ' + str(len(W))
    
    
    
    #print 'reading V...'
    V = read()
    #print 'preprocessing V...'
    V = preprocess(V)
    
    #print 'rank: ' + str(rank)
    
    matrix = Matrix.from_file(matrix_filename)
    
    #print 'matrix: ' + str(matrix.data)
    #print 'matrix: ' + str(V) + '\n .data: ' + str(V)
    
    model = model_factory(V, rank=rank)
    model_fit = model()
    
    W = model_fit.basis()
    H = model_fit.coef()
    
    print 'W: ' + str(W)
    print 'length: ' + str(len(W))
    
    
    
    
    nmf = implementation_map[implementation]

    if nmf is None:
        print "Unknown implementation"
        exit(1)


    
    print 'func: ' + str(func)
    
    if func != 'standard':
        (W, H) = nmf(V, rank)
    
        W = Matrix(W)
        H = Matrix(H)
        
        print "\n\n\n\nW: \n" + str(W)
    
        print 'w_filename: ' + w_filename
        W.to_file(w_filename)
        H.to_file(h_filename)
    '''
        
    '''
    nmf = implementation_map[implementation]

    if nmf is None:
        print "Unknown implementation"
        exit(1)

    if func == 'standard':
        (W, H) = nmf(matrix.data, rank)
    
        W = Matrix(W)
        H = Matrix(H)
    
        print implementation
    
        W.to_file(w_filename)
        H.to_file(h_filename)
    else:
        print '\n\n\n\nnon standard\n\n\n\n\n'
        
        print 'matrix1: ' + str(matrix1)
        print 'matrix2: ' + str(matrix2)
        
        type1 = nimfa.Nmf
        type2 = nimfa.Icm
        score = find_score(matrix.data, rank, type1, type2)
    
        
        (W, H) = nmf(matrix.data, rank)
    
    
        W = Matrix(W)
        H = Matrix(H)
    
        print implementation
    
        W.to_file(w_filename)
        H.to_file(h_filename)

    '''
    
if __name__ == "__main__":
    import argparse

    print 'args'
    parser = argparse.ArgumentParser()
    parser.add_argument("--implementation")
    parser.add_argument("--matrix-filename")
    parser.add_argument("--w-filename")
    parser.add_argument("--h-filename")
    parser.add_argument("--rank", type=int)
    parser.add_argument("--func")
    parser.add_argument("--matrix1")
    parser.add_argument("--matrix2")
    parser.add_argument("--type")
    
    args = parser.parse_args()

    print 'args'
    main(**vars(args))
