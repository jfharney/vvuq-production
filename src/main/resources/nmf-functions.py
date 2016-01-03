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



